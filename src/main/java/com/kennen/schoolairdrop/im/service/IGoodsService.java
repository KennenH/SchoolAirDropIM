package com.kennen.schoolairdrop.im.service;

import com.kennen.schoolairdrop.im.bean.GoodsDetail;
import com.kennen.schoolairdrop.im.bean.GoodsList;
import com.kennen.schoolairdrop.im.response.ResponseResult;

/**
 * @author kennen
 * @date 2020/12/28 12:24
 */
public interface IGoodsService {

    /**
     * 获取附近物品
     */
    GoodsList getNearByGoods();

    /**
     * 获取我的在售
     */
    GoodsList getMySelling(int userID);

    /**
     * 获取物品详细信息
     *
     * @param goodsID 物品id
     */
    GoodsDetail getGoodsDetail(int goodsID);

    /**
     * 关键字搜索物品
     */
    GoodsList searchGoodsByKey(String key);

    /**
     * 上传物品
     *
     * @param title        标题
     * @param cover        封面
     * @param picSet       图片集
     * @param price        价格
     * @param isQuotable   是否可报价
     * @param isSecondHand 是否二手
     * @param description  物品详细描述
     */
    ResponseResult addNewGoods(String title,
                               String cover,
                               String picSet,
                               String price,
                               int isQuotable,
                               int isSecondHand,
                               String description);

    /**
     * 删除物品
     *
     * @param goodsID 物品id
     */
    ResponseResult deleteGoods(int goodsID);
}
