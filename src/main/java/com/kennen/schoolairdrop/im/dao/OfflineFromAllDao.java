package com.kennen.schoolairdrop.im.dao;

import com.kennen.schoolairdrop.im.pojo.OfflineFromAll;
import com.kennen.schoolairdrop.im.utils.Constants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author kennen
 * @date 2021/1/27 15:54
 */
public interface OfflineFromAllDao extends JpaRepository<OfflineFromAll, String> {

    @Query(nativeQuery = true, value = "select i1.* " +
            " from offline_from_all?1 i1 " +
            "         left outer join offline_from_all?1 i2 " +
            "                         on (i1.sender_id = i2.sender_id and i1.offline_id < i2.offline_id) " +
            " where i1.receiver_id = ?2 " +
            " group by i1.offline_id , i1.sender_id " +
            " HAVING COUNT(*) < " + Constants.ONE_PAGE_NUM +
            " order by sender_id, offline_id ")
    List<OfflineFromAll> findFromAll(int table, String receiverID);
}
