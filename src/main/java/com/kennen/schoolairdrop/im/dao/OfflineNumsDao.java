package com.kennen.schoolairdrop.im.dao;

import com.kennen.schoolairdrop.im.pojo.OfflineNum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author kennen
 * @date 2020/12/15 13:51
 */
public interface OfflineNumsDao extends JpaRepository<OfflineNum, String>, JpaSpecificationExecutor<OfflineNum> {

    @Query(nativeQuery = true, value = "select * from offline_nums where offline_num > 0 and receiver_id = ?")
    List<OfflineNum> findAllByReceiverID(String receiverID);

    @Modifying
    @Query(nativeQuery = true, value = "update offline_nums set offline_num = 0 where receiver_id = ? and sender_id = ?")
    int ackOfflineNums(String receiverID, String senderID);
}
