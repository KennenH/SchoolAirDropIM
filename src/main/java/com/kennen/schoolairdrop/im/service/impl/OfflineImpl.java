package com.kennen.schoolairdrop.im.service.impl;

import com.kennen.schoolairdrop.im.bean.ProtocalWithTime;
import com.kennen.schoolairdrop.im.dao.OfflineDao;
import com.kennen.schoolairdrop.im.dao.OfflineNumsDao;
import com.kennen.schoolairdrop.im.pojo.Offline;
import com.kennen.schoolairdrop.im.pojo.OfflineNum;
import com.kennen.schoolairdrop.im.utils.MessageUtil;
import com.kennen.schoolairdrop.im.utils.Redis;
import com.kennen.schoolairdrop.im.dao.AccessTokenDao;
import com.kennen.schoolairdrop.im.pojo.AccessToken;
import com.kennen.schoolairdrop.im.response.ResponseResult;
import com.kennen.schoolairdrop.im.service.IOfflineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.Collection;
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
    private Redis redis;

    @Override
    public ResponseResult getOfflineMessageByID(String token, String senderID) {
        token = token.substring(7);
        AccessToken accessToken = accessTokenDao.findOneByAccessToken(token);

        if (accessToken != null) {
            String receiverID = accessToken.getUserID();
            // 取出在redis中的离线消息集合
            Map<Object, Object> redisMessages = redis.getHashMap(token);
            // 将取出的redis离线消息放入数据库中
            if (redisMessages.size() > 0) {
                offlineDao.saveAll(MessageUtil.protocalToOffline(redisMessages.values()));
            }
            // 删除redis中的离线缓存
            for (Map.Entry<Object, Object> entry : redisMessages.entrySet()) {
                redis.deleteHash(token, entry.getKey());
            }

//            List<Offline> dataBaseMessages = offlineDao.getAllByReceiverID(receiverID.hashCode() % 10, receiverID);

            // todo 分页获取离线消息
            // 按发送时间倒序排序

            return ResponseResult.SUCCESS("离线消息获取成功");
        }
        return ResponseResult.FAILED("用户不存在" + token);
    }

    @Override
    public ResponseResult offlineMessageAck(String token, List<String> fingerprints) {
        AccessToken accessToken = accessTokenDao.findOneByAccessToken(token);
        if (accessToken != null) {
            String receiverID = accessToken.getUserID();
            String fingerPrintsSql = MessageUtil.listToStringSplitWithDot(fingerprints);
            int acks = offlineDao.offlineMessagesAck(receiverID.hashCode() % 10, fingerPrintsSql);

            int totalSize = fingerprints.size();
            if (acks == totalSize) {
                return ResponseResult.SUCCESS("所有离线消息ack成功").setData(true);
            } else {
                return ResponseResult.SUCCESS("共有" + totalSize + "ack失败").setData(true);
            }
        } else {
            return ResponseResult.FAILED("用户不存在或者鉴权信息已过期");
        }
    }

    @Override
    public ResponseResult getOfflineNums(String token) {
        AccessToken accessToken = accessTokenDao.findOneByAccessToken(token);
        if (accessToken != null) {
            List<OfflineNum> offlineNums = offlineNumsDao.findAllByReceiverID(accessToken.getUserID());
            return ResponseResult.SUCCESS("未读离线消息数量获取成功").setData(offlineNums);
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

            redis.setHash(receiverID, fingerPrint, protocalWithTime);
            return true;
        }

        return false;
    }

    @Override
    public ResponseResult offlineNumsAck(String receiverID, String senderID) {
        // 未读消息红点抹除
        int success = offlineNumsDao.ackOfflineNums(receiverID, senderID);
        return success != 0 ? ResponseResult.SUCCESS("未读离线消息ack成功").setData(true) : ResponseResult.FAILED("此对话没有未读需要ack或ack出错").setData(false);
    }
}
