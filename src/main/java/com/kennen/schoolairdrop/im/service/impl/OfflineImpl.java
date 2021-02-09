package com.kennen.schoolairdrop.im.service.impl;

import com.kennen.schoolairdrop.im.bean.ProtocalWithTime;
import com.kennen.schoolairdrop.im.dao.*;
import com.kennen.schoolairdrop.im.pojo.*;
import com.kennen.schoolairdrop.im.utils.Constants;
import com.kennen.schoolairdrop.im.utils.MessageUtil;
import com.kennen.schoolairdrop.im.response.ResponseResult;
import com.kennen.schoolairdrop.im.service.IOfflineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class OfflineImpl implements IOfflineService {

    @Autowired
    private AccessTokenDao accessTokenDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private OfflineDao offlineDao;

    @Autowired
    private OfflineNumsDao offlineNumsDao;

    @Autowired
    private OfflineNumsDetailDao offlineNumsDetailDao;

    @Autowired
    private OfflineFromAllDao offlineFromAllDao;

    @Override
    public ResponseResult getOfflineSecondaryPull(String token, String senderID, String fingerprintLatest, List<String> fingerprintsToAck) {
        // 去掉token前面的前缀
        token = token.substring(7);
        AccessToken accessToken = accessTokenDao.findOneByAccessToken(token);
        if (accessToken != null) {
            String receiverID = String.valueOf(accessToken.getUserID());
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
        // 移动端收取到的结果不论失败还是成功，bean的结构必须要一致，否则会导致 IllegalStateException
        return ResponseResult.FAILED("用户不存在或验证信息已过期 " + token);
    }

    @Override
    public ResponseResult getOfflinePrimaryPull(String token, String senderID) {
        token = token.substring(7);
        AccessToken accessToken = accessTokenDao.findOneByAccessToken(token);

        if (accessToken != null) {
            String receiverID = String.valueOf(accessToken.getUserID());
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
    private void offlineMessageAck(String receiverID, List<String> fingerprints) {
        // 将需要ack的消息数组转换为以单引号引用，逗号分隔的指纹字符串
        String fingerPrintsSql = MessageUtil.listToStringSplitWithDot(fingerprints);

        // ack消息本体
        offlineDao.offlineMessagesAck(
                receiverID.hashCode() % Constants.OFFLINE_TABLE_NUMS,
                fingerPrintsSql);
    }

    @Override
    public ResponseResult getOfflineNum(String token) {
        token = token.substring(7);
        AccessToken accessToken = accessTokenDao.findOneByAccessToken(token);
        if (accessToken != null) {
            String receiverID = String.valueOf(accessToken.getUserID());
            int table = receiverID.hashCode() % Constants.OFFLINE_TABLE_NUMS;

            // 获取来自所有用户发送给receiver的消息数量，已经以senderID进行排序
            List<OfflineNumsDetail> offlineNumsDetails = offlineNumsDetailDao.findAllByID(table, receiverID);

            // 获取来自以上所有用户的最新10条消息，已经以senderID进行排序
            List<OfflineFromAll> offlineFromAlls = offlineFromAllDao.findFromAll(table, receiverID);

            // 组装消息数量和来自各个用户的10条消息
            // 这里由于以上两组数据都已经通过senderID进行排序，因此在这里可以以线性的时间复杂度完成组装操作
            // 时间复杂度与来自所有用户的消息的数量成正比
            int index = 0;
            for (OfflineFromAll offline : offlineFromAlls) {
                // 第index个发送者
                OfflineNumsDetail offlineNumsDetail = offlineNumsDetails.get(index);

                // 获取发送者用户信息
                String senderId = offlineNumsDetail.getSender_id();
                UserInfo senderInfo = userDao.getUserInfoByID(senderId);
                offlineNumsDetail.setSender_info(new OfflineNumsDetail.SenderInfo(
                        senderInfo.getUser_id(),
                        senderInfo.getUser_name(),
                        senderInfo.getUser_avatar()));

                // 复制该离线消息到OfflineNumsDetail可以存放的子类中
                OfflineNumsDetail.Offline bean = new OfflineNumsDetail.Offline();
                BeanUtils.copyProperties(offline, bean);

                if (offline.getSender_id().equals(senderId)) {
                    // 若当前的消息sender_id与当前第index发送者的id一致，则将其放入该发送者的offline中
                    offlineNumsDetail.getOffline().add(bean);
                } else {
                    // 否则如果当前消息的sender_id与当前发送者的id不一致，则直接放入下一个发送者offline中
                    offlineNumsDetails.get(++index).getOffline().add(bean);
                }
            }

            return ResponseResult.SUCCESS("离线消息数量获取成功").setData(offlineNumsDetails);
        } else {
            return ResponseResult.FAILED("用户不存在或者鉴权信息已过期 token -- > " + token);
        }
    }

    @Override
    public boolean saveOfflineMessage(ProtocalWithTime protocalWithTime) {
        final String senderID = protocalWithTime.getFrom();
        // 从数据库中查询发送者是否存在
        final UserInfo userInfo = userDao.getUserInfoByID(senderID);

        // token存在，发送者用户存在
        if (userInfo != null) {
            // 将消息发送者token换为发送者id
            String fingerPrint = protocalWithTime.getFp();
            String receiverID = protocalWithTime.getTo();

            int client = receiverID.compareTo(senderID);
            // 更新两个用户之间的最新离线消息
            if (client < 0) {
                offlineNumsDao.updateLatestOfflineClientA(receiverID, senderID, fingerPrint);
            } else if (client > 0) {
                offlineNumsDao.updateLatestOfflineClientB(receiverID, senderID, fingerPrint);
            } else {
                return false;
            }

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
