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

    @Modifying
    @Query(nativeQuery = true, value = "update offline_nums set offline_num_to_a = 0 where client_a_id = ?1 and client_b_id = ?2")
    int ackOfflineNumsClientA(String receiverID, String senderID);

    @Modifying
    @Query(nativeQuery = true, value = "update offline_nums set offline_num_to_b = 0 where client_a_id = ?2 and client_b_id = ?1")
    int ackOfflineNumsClientB(String receiverID, String senderID);

    @Modifying
    @Query(nativeQuery = true, value = "insert into offline_nums (client_a_id, client_b_id, offline_num_to_a, fingerprint, latest_sender) " +
            "values (?1, ?2, offline_num_to_a + 1, ?3, 0) " +
            "on duplicate key update offline_num_to_a = offline_num_to_a + 1, fingerprint = ?3, latest_sender = 0")
    void updateLatestOfflineClientA(String receiverID, String senderID, String fingerprint);

    @Modifying
    @Query(nativeQuery = true, value = "insert into offline_nums (client_a_id, client_b_id, offline_num_to_b, fingerprint, latest_sender) " +
            "values (?2, ?1, offline_num_to_b + 1, ?3, 1) " +
            "on duplicate key update offline_num_to_b = offline_num_to_b + 1, fingerprint = ?3, latest_sender = 1")
    void updateLatestOfflineClientB(String receiverID, String senderID, String fingerprint);
}