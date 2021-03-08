package com.kennen.schoolairdrop.im.service.impl;

import com.kennen.schoolairdrop.im.bean.ProtocalWithTime;
import com.kennen.schoolairdrop.im.dao.*;
import com.kennen.schoolairdrop.im.pojo.*;
import com.kennen.schoolairdrop.im.service.RestTemplateService;
import com.kennen.schoolairdrop.im.utils.Constants;
import com.kennen.schoolairdrop.im.response.ResponseResult;
import com.kennen.schoolairdrop.im.service.IOfflineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private RestTemplateService restTemplateService;

    @Override
    public ResponseResult getOfflineBefore(String token, String senderID, long startTime) {
        // 去掉token前面的 Bearer 前缀
        token = token.substring(7);
        AccessToken accessToken = accessTokenDao.findOneByAccessToken(token);

        if (accessToken != null) {
            String receiverID = String.valueOf(accessToken.getUserID());
            int tableNum = receiverID.hashCode() % Constants.OFFLINE_TABLE_NUMS;

            // 先ack临界时间包括自己之后的所有消息
            offlineMessageAck(senderID, receiverID, startTime);

            // 获取指定时间之前的离线消息，按发送时间倒序排序
            List<Offline> dataBaseMessages = offlineDao.findBefore(
                    tableNum,
                    receiverID,
                    senderID,
                    startTime);

            return ResponseResult.SUCCESS("离线消息获取成功").setData(dataBaseMessages);
        }
        // 移动端收取到的结果不论失败还是成功，bean的结构必须要一致，否则会导致 IllegalStateException
        return ResponseResult.FAILED("用户不存在或验证信息已过期 " + token);
    }

    /**
     * ack 消息
     */
    private void offlineMessageAck(String senderID, String receiverID, long ack) {
        // ack大于临界时间的消息
        offlineDao.offlineMessagesAck(
                receiverID.hashCode() % Constants.OFFLINE_TABLE_NUMS,
                receiverID,
                senderID,
                ack);

        int sender = Integer.parseInt(senderID);
        int receiver = Integer.parseInt(receiverID);
        // 此处是给receiver进行离线消息数量的ack，因此receiver小就调用ClientA，大就调用ClientB
        if (receiver < sender) {
            offlineNumsDao.ackReceiverOfflineNumsAsClientA(receiverID, senderID);
        } else if (receiver > sender) {
            offlineNumsDao.ackReceiverOfflineNumsAsClientB(receiverID, senderID);
        }
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

            if (offlineNumsDetails.size() > 0) {
                // 获取来自以上所有用户的最新10条消息，已经以senderID进行排序
                List<OfflineFromAll> offlineFromAlls = offlineFromAllDao.findFromAll(table, receiverID);

                // 组装消息数量和来自各个用户的10条消息，这里由于以上两组数据都已经通过senderID进行排序，因此在这里可以以线性的时间复杂度完成组装操作
                // 时间复杂度与来自所有用户的消息的数量成正比，即仅取决于下面这个for循环
                int index = 0;
                // 第index个发送者
                OfflineNumsDetail offlineNumsDetail = offlineNumsDetails.get(index);
                // 获取发送者用户信息
                String senderId = offlineNumsDetail.getSender_id();
                for (OfflineFromAll offlineFromAll : offlineFromAlls) {
                    // 装配该离线消息到OfflineNumsDetail可以存放的子类中
                    OfflineNumsDetail.Offline bean = new OfflineNumsDetail.Offline();
                    BeanUtils.copyProperties(offlineFromAll, bean);

                    if (offlineFromAll.getSender_id().equals(senderId)) {
                        // 若当前的消息sender_id与当前第index发送者的id一致，则将其放入该发送者的offline中
                        offlineNumsDetail.getOffline().add(bean);
                    } else {
                        // 否则如果当前消息的sender_id与当前发送者的id不一致，则直接放入下一个发送者offline中
                        offlineNumsDetails.get(++index).getOffline().add(bean);
                        // 将用户信息切换至下一个
                        offlineNumsDetail = offlineNumsDetails.get(index);
                        senderId = offlineNumsDetail.getSender_id();
                    }

                    // 装配离线消息发送者的用户信息
                    if (offlineNumsDetail.getSender_info() == null) {
                        UserInfo senderInfo = userDao.getUserInfoByID(senderId);
                        offlineNumsDetail.setSender_info(new OfflineNumsDetail.SenderInfo(
                                senderInfo.getUser_id(),
                                senderInfo.getUser_name(),
                                senderInfo.getUser_avatar()));
                    }
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

            int receiver = Integer.parseInt(receiverID);
            int sender = Integer.parseInt(senderID);
            // 更新两个用户之间的最新离线消息
            if (receiver < sender) {
                offlineNumsDao.updateLatestOfflineReceiverClientA(receiverID, senderID, fingerPrint);
            } else if (receiver > sender) {
                offlineNumsDao.updateLatestOfflineReceiverClientB(receiverID, senderID, fingerPrint);
            } else {
                return false;
            }

            int table = receiverID.hashCode() % Constants.OFFLINE_TABLE_NUMS;
            try {
                saveOffline(table, protocalWithTime);
            } catch (Exception e) {
                // 未知错误，离线消息存储失败
                return false;
            }

            // 离线消息存储成功，发送push给接收者
            restTemplateService.pushNotification(receiverID, userInfo.getUser_name().concat(":").concat(protocalWithTime.getDataContent()));
            return true;
        }

        // 发送放验证信息非法，消息未被存储
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