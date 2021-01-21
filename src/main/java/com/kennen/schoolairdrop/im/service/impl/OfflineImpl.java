package com.kennen.schoolairdrop.im.service.impl;

import com.kennen.schoolairdrop.im.bean.ProtocalWithTime;
import com.kennen.schoolairdrop.im.dao.OfflineDao;
import com.kennen.schoolairdrop.im.dao.OfflineNumsDao;
import com.kennen.schoolairdrop.im.dao.OfflineNumsDetailDao;
import com.kennen.schoolairdrop.im.pojo.Offline;
import com.kennen.schoolairdrop.im.pojo.OfflineNum;
import com.kennen.schoolairdrop.im.pojo.OfflineNumsDetail;
import com.kennen.schoolairdrop.im.utils.Constants;
import com.kennen.schoolairdrop.im.utils.MessageUtil;
import com.kennen.schoolairdrop.im.utils.Redis;
import com.kennen.schoolairdrop.im.dao.AccessTokenDao;
import com.kennen.schoolairdrop.im.pojo.AccessToken;
import com.kennen.schoolairdrop.im.response.ResponseResult;
import com.kennen.schoolairdrop.im.service.IOfflineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
public class OfflineImpl implements IOfflineService {

    @Autowired
    private AccessTokenDao accessTokenDao;

    @Autowired
    private OfflineDao offlineDao;

    @Autowired
    private OfflineNumsDao offlineNumsDao;

    @Autowired
    private OfflineNumsDetailDao offlineNumsDetailDao;

    @Autowired
    private Redis redis;

    @Override
    public ResponseResult getOfflineSecondaryPull(String token, String senderID, String fingerprintLatest, List<String> fingerprintsToAck) {
        // 去掉token前面的前缀
        token = token.substring(7);
        AccessToken accessToken = accessTokenDao.findOneByAccessToken(token);

        if (accessToken != null) {
            String receiverID = accessToken.getUserID();
            int tableNum = receiverID.hashCode() % Constants.OFFLINE_TABLE_NUMS;

            // 通过第一条和最后一条聊天记录的指纹码来找到开始和结束的临界id
            Offline latest = offlineDao.findOneByFingerPrint(tableNum, fingerprintLatest);

            // 临界消息的id
            int offlineLatest = 0;
            if (latest != null) {
                offlineLatest = latest.getOfflineID();
            }

            // 有需要ack的消息列表
            if (fingerprintsToAck != null && fingerprintsToAck.size() > 0) {
                offlineMessageAck(receiverID, fingerprintsToAck);
            }

            // 分页获取离线消息，按发送时间倒序排序
            List<Offline> dataBaseMessages = offlineDao.findBeforeFrom(
                    tableNum,
                    receiverID,
                    senderID,
                    offlineLatest);

            return ResponseResult.SUCCESS("离线消息获取成功").setData(dataBaseMessages);
        }
        return ResponseResult.FAILED("用户不存在或验证信息已过期 " + token);
    }

    @Override
    public ResponseResult getOfflinePrimaryPull(String token, String senderID) {
        token = token.substring(7);
        AccessToken accessToken = accessTokenDao.findOneByAccessToken(token);

        if (accessToken != null) {
            String receiverID = accessToken.getUserID();
            int tableNum = receiverID.hashCode() % Constants.OFFLINE_TABLE_NUMS;

            int client = receiverID.compareTo(senderID);
            // 未读消息ack，receiverID大则调用clientA，否则调用clientB
            if (client < 0) {
                offlineNumsDao.ackOfflineNumsClientA(receiverID, senderID);
            } else if (client > 0) {
                offlineNumsDao.ackOfflineNumsClientB(receiverID, senderID);
            } else {
                return ResponseResult.FAILED("不可发送消息给自己");
            }

            // 分页获取离线消息，按发送时间倒序排序
            List<Offline> dataBaseMessages = offlineDao.findLatest(
                    tableNum,
                    receiverID,
                    senderID);

            return ResponseResult.SUCCESS("离线消息获取成功").setData(dataBaseMessages);
        }
        return ResponseResult.FAILED("用户不存在或验证信息已过期 " + token);
    }

    /**
     * 消息本体ack
     */
    private int offlineMessageAck(String receiverID, List<String> fingerprints) {
        // 将需要ack的消息数组转换为以单引号引用，逗号分隔的指纹字符串
        String fingerPrintsSql = MessageUtil.listToStringSplitWithDot(fingerprints);

        // ack消息本体
        return offlineDao.offlineMessagesAck(
                receiverID.hashCode() % Constants.OFFLINE_TABLE_NUMS,
                fingerPrintsSql);
    }

    @Override
    public ResponseResult getOfflineSnapshot(String token) {
        token = token.substring(7);
        AccessToken accessToken = accessTokenDao.findOneByAccessToken(token);
        if (accessToken != null) {
            String receiverID = accessToken.getUserID();
            int table = receiverID.hashCode() % Constants.OFFLINE_TABLE_NUMS;

            // todo 使用redis优化离线消息即时存储
//            // 取出在redis中的离线消息集合
//            Map<Object, Object> redisMessages = redis.getHashMap(token);
//
//            // 若redis中有消息缓存则转存消息并删除响应缓存
//            if (redisMessages.size() > 0) {
//                // 将取出的redis离线消息放入数据库中
//                offlineDao.saveAll(MessageUtil.protocalToOffline(redisMessages.values()));
//
//                // 删除redis中的离线缓存
//                for (Map.Entry<Object, Object> entry : redisMessages.entrySet()) {
//                    redis.deleteHash(token, entry.getKey());
//                }
//            }

            List<OfflineNumsDetail> offlineNumsDetails = offlineNumsDetailDao.findAllByID(table, receiverID);
            return ResponseResult.SUCCESS("离线消息快照获取成功").setData(offlineNumsDetails);
        } else {
            return ResponseResult.FAILED("用户不存在或者鉴权信息已过期").setData(false);
        }
    }

    @Override
    public boolean saveOfflineMessage(ProtocalWithTime protocalWithTime) {
        final String senderToken = protocalWithTime.getFrom();
        // 从数据库中查询发送者是否存在
        final AccessToken userToken = accessTokenDao.findOneByAccessToken(senderToken);

        // token存在，发送者用户存在
        if (userToken != null) {
            // 将消息发送者token换为发送者id
            protocalWithTime.setFrom(userToken.getUserID());
            String fingerPrint = protocalWithTime.getFp();
            String receiverID = protocalWithTime.getTo();
            String senderID = protocalWithTime.getFrom();

            int client = receiverID.compareTo(senderID);
            // 更新两个用户之间的最新离线消息
            if (client < 0) {
                offlineNumsDao.updateLatestOfflineClientA(receiverID, senderID, fingerPrint);
            } else if (client > 0) {
                offlineNumsDao.updateLatestOfflineClientB(receiverID, senderID, fingerPrint);
            } else {
                return false;
            }

            // 保存离线消息 todo 使用redis优化离线消息存储
//            redis.setHash(receiverID, fingerPrint, protocalWithTime);

            int table = receiverID.hashCode() % Constants.OFFLINE_TABLE_NUMS;
            try {
                saveOffline(table, protocalWithTime);
            } catch (Exception e) {
                log.info("离线消息存储失败");
                return false;
            }

            log.info("离线消息已存储 " + protocalWithTime.getDataContent());
            return true;
        }

        log.info("发送方验证信息非法，消息未被存储");
        return false;
    }

    private void saveOffline(int table, ProtocalWithTime protocalWithTime) {
        offlineDao.saveOffline(
                table,
                protocalWithTime.getFp(),
                protocalWithTime.getFrom(),
                protocalWithTime.getTo(),
                protocalWithTime.getTypeu(),
                protocalWithTime.isReceived(),
                protocalWithTime.getSendTime(),
                protocalWithTime.getDataContent());
    }
}