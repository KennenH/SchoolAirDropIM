package com.kennen.schoolairdrop.im.dao;


import com.kennen.schoolairdrop.im.pojo.Offline;
import com.kennen.schoolairdrop.im.utils.Constants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface OfflineDao extends JpaRepository<Offline, String>, JpaSpecificationExecutor<Offline> {

    /**
     * 二次拉取
     * 默认一页为10条消息
     * 以时间为依据找小于（早于，旧于且不包含本身）临界时间的消息
     */
    @Query(value = "select * from offline?1 where received = 0 and receiver_id = ?2 and sender_id = ?3" +
            " and send_time < ?4 order by offline_id desc limit " + Constants.ONE_PAGE_NUM,
            nativeQuery = true)
    List<Offline> findBefore(int table,
                             String receiverID,
                             String senderID,
                             long fromTime);

    /**
     * 消息ack
     *
     * @param table     表
     * @param startTime 将消息时间大于等于（晚于、新于并包含本身）这个临界时间的消息都ack
     */
    @Modifying
    @Query(value = "update offline?1 set received = 1 where receiver_id = ?2 and sender_id = ?3 and " +
            " send_time >= ?4 ", nativeQuery = true)
    void offlineMessagesAck(int table, String receiverID, String senderID, long startTime);

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
                     long sendTime,
                     String message);
}