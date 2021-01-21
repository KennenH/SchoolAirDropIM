package com.kennen.schoolairdrop.im.dao;

import com.kennen.schoolairdrop.im.pojo.OfflineNumsDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author kennen
 * @date 2021/1/20 17:18
 */
public interface OfflineNumsDetailDao extends JpaRepository<OfflineNumsDetail, String> {

    /**
     * 将以a的身份收到的所有记录和以b的身份收到的所有记录合并后就是所有用户发给自己的消息快照
     * <p>
     * detail视图后缀数字对应的就是offline表后缀对应的数字
     * 以两种身份都要查一次再合并是因为不知道发送者id是否小于我的id
     * 在离线消息保存的时候在offline_nums表中成为a还是b是id的大小所决定的
     * 因此只要大小都查一次就可以保证获取来自所有用户的消息快照
     */
    @Query(nativeQuery = true, value = "select * from offline_nums_detail_a?1 where receiver_id = ?2 " +
            "union all " +
            "select * from offline_nums_detail_b?1 where receiver_id = ?2")
    List<OfflineNumsDetail> findAllByID(int table, String receiverID);
}
