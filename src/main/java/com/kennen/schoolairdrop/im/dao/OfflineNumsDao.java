package com.kennen.schoolairdrop.im.dao;

import com.kennen.schoolairdrop.im.pojo.OfflineNum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author kennen
 * @date 2020/12/15 13:51
 */
public interface OfflineNumsDao extends JpaRepository<OfflineNum, String>, JpaSpecificationExecutor<OfflineNum> {

    /**
     * 故方法名思议，为receiver最为ClientA进行ack，ClientA即作为id小的一方
     * 为receiver进行离线消息数量的ack，当receiver小于sender的时候调用该方法按参数名填入两者即可
     *
     * @param receiverID receiver 小于 sender
     * @param senderID   sender 大于 receiver
     */
    @Modifying
    @Query(nativeQuery = true, value = "update offline_nums set offline_num_to_a = 0 where client_a_id = ?1 and client_b_id = ?2")
    void ackReceiverOfflineNumsAsClientA(String receiverID, String senderID);

    /**
     * 故方法名思议，为receiver最为ClientB进行ack，ClientB即作为id大的一方
     * 为receiver进行离线消息数量的ack，当receiver大于sender的时候调用该方法按参数名填入两者即可
     *
     * @param receiverID receiver 大于 sender
     * @param senderID   sender 小于 receiver
     */
    @Modifying
    @Query(nativeQuery = true, value = "update offline_nums set offline_num_to_b = 0 where client_a_id = ?2 and client_b_id = ?1")
    void ackReceiverOfflineNumsAsClientB(String receiverID, String senderID);

    @Modifying
    @Query(nativeQuery = true, value = "insert into offline_nums (client_a_id, client_b_id, offline_num_to_a, fingerprint, latest_sender) " +
            "values (?1, ?2, offline_num_to_a + 1, ?3, 1) " +
            "on duplicate key update offline_num_to_a = offline_num_to_a + 1, fingerprint = ?3, latest_sender = 1")
    void updateLatestOfflineClientA(String receiverID, String senderID, String fingerprint);

    @Modifying
    @Query(nativeQuery = true, value = "insert into offline_nums (client_a_id, client_b_id, offline_num_to_b, fingerprint, latest_sender) " +
            "values (?2, ?1, offline_num_to_b + 1, ?3, 0) " +
            "on duplicate key update offline_num_to_b = offline_num_to_b + 1, fingerprint = ?3, latest_sender = 0")
    void updateLatestOfflineClientB(String receiverID, String senderID, String fingerprint);
}