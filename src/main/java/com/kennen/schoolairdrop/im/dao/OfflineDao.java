package com.kennen.schoolairdrop.im.dao;


import com.kennen.schoolairdrop.im.bean.OfflineID;
import com.kennen.schoolairdrop.im.pojo.Offline;
import com.kennen.schoolairdrop.im.utils.Constants;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface OfflineDao extends JpaRepository<Offline, String>, JpaSpecificationExecutor<Offline> {

    /**
     * @deprecated 使用下面的getAllBetweenFromToByPage
     */
    @Deprecated
    @Query(value = "select * from offline?1 where received = 0 and receiver_id = ?2", nativeQuery = true)
    List<Offline> getAllByReceiverID(int table, String userID);

    /**
     * 二次拉取
     * 默认一页为10条消息
     * 这里不是以时间为依据找临界消息之后的消息，而是通过id，简单快速
     */
    @Query(value = "select * from offline?1 where received = 0 and receiver_id = ?2 and sender_id = ?3" +
            " and offline_id < ?4 order by offline_id desc limit " + Constants.ONE_PAGE_NUM,
            nativeQuery = true)
    List<Offline> findBeforeFrom(int table,
                                 String receiverID,
                                 String senderID,
                                 int fromID);

    /**
     * 首次拉取
     * 上层应用在点击进入与某人的聊天界面时调用
     */
    @Query(value = "select * from offline?1 where received = 0 and receiver_id = ?2 and sender_id = ?3" +
            " order by offline_id desc limit " + Constants.ONE_PAGE_NUM,
            nativeQuery = true)
    List<Offline> findLatest(int table,
                             String receiverID,
                             String senderID);

    /**
     * 替代首次拉取
     * 上层应用在进入app时调用一次或者在app长时间休眠之后调用即可
     * 获取所有用户发送给receiver的最新10条离线消息
     */
    @Query(value = "select * from offline_num_detail?1 where receiver_id = ?2 limit " + Constants.ONE_PAGE_NUM, nativeQuery = true)
    List<Offline> findAllByReceiverID(int table,
                                      String receiverID);

    /**
     * 对具体消息进行ack
     *
     * @param table        表
     * @param fingerPrints 以单引号引用，逗号分隔的消息指纹串，形式为 'x','y','z'
     */
    @Modifying
    @Query(value = "update offline?1 set received = 1 where finger_print in (?2)", nativeQuery = true)
    int offlineMessagesAck(int table, String fingerPrints);

    /**
     * 通过消息指纹找到消息本体
     *
     * @param table       表
     * @param fingerprint 消息指纹
     */
    @Query(value = "select * from offline?1 where finger_print = ?2", nativeQuery = true)
    Offline findOneByFingerPrint(int table, String fingerprint);

    /**
     * 保存离线消息
     */
    @Modifying
    @Query(value = "insert into offline?1 (finger_print, sender_id, receiver_id, message_type, received, send_time, `message`) " +
            "values (?2, ?3, ?4, ?5, ?6, ?7, ?8)", nativeQuery = true)
    void saveOffline(int table,
                     String fingerprint,
                     String senderID,
                     String receiverID,
                     int messageType,
                     int received,
                     Date sendTime,
                     String message);
}