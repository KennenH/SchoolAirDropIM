package com.kennen.schoolairdrop.im.dao;

import com.kennen.schoolairdrop.im.pojo.GoodsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author kennen
 * @date 2021/1/4 14:22
 */
public interface GoodsDao extends JpaSpecificationExecutor<GoodsInfo>, JpaRepository<GoodsInfo, Integer> {

    @Query(value = "select * from `goods_info` where goods_id = ?", nativeQuery = true)
    GoodsInfo findByID(int goodsID);
}
