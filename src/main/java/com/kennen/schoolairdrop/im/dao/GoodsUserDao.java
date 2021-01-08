package com.kennen.schoolairdrop.im.dao;

import com.kennen.schoolairdrop.im.pojo.GoodsUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author kennen
 * @date 2021/1/4 10:24
 */
public interface GoodsUserDao extends JpaRepository<GoodsUserInfo, Integer>, JpaSpecificationExecutor<GoodsUserInfo> {

    @Query(value = "select * from `goods_user_info` order by goods_id desc", nativeQuery = true)
    List<GoodsUserInfo> findAllNearBy();

    @Query(value = "select * from `goods_user_info` where goods_id = ?", nativeQuery = true)
    GoodsUserInfo findByID(int id);

    @Query(value = "select * from `goods_user_info` where goods_sellerid = ?", nativeQuery = true)
    List<GoodsUserInfo> findAllByUserID(int userID);
}
