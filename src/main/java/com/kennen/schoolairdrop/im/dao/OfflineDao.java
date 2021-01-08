package com.kennen.schoolairdrop.im.dao;


import com.kennen.schoolairdrop.im.pojo.Offline;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface OfflineDao extends JpaRepository<Offline, String>, JpaSpecificationExecutor<Offline> {

    @Query(value = "select * from offline?1 where finger_print = ?2", nativeQuery = true)
    Offline getOneByFingerPrint(int table, String fingerPrint);

    @Deprecated
    @Query(value = "select * from offline?1 where received = 0 and receiver_id = ?2", nativeQuery = true)
    List<Offline> getAllByReceiverID(int table, String userID);

//    @Query(value = "select * from offline?1 where received = 0 and receiver_id = ?2 and sender_id = ?3" +
//            " and send_time between :dateFrom and :dateTo",
//            nativeQuery = true)
//    List<Offline> getAllBetweenFromToByPage(int table,
//                                            String receiverID,
//                                            String senderID,
//                                            @Param("dateFrom") Date dateFrom,
//                                            @Param("dateTo") Date dateTo);

    @Query(value = "select * from offline?1 where received = 0 and receiver_id = ?2 and sender_id = ?3" +
            " and send_time ",
            nativeQuery = true)
    List<Offline> getAllNewMessagesByPage(int table,
                                          String receiverID,
                                          String senderID,
                                          @Param("latestMessageTime") Date latestMessageTime);

    @Modifying
    @Query(value = "update offline?1 set received = 1 where finger_print in (?2)", nativeQuery = true)
    int offlineMessagesAck(int table, String fingerPrints);
}