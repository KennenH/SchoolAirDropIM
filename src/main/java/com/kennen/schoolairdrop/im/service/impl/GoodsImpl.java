package com.kennen.schoolairdrop.im.service.impl;

import com.kennen.schoolairdrop.im.bean.GoodsDetail;
import com.kennen.schoolairdrop.im.bean.GoodsList;
import com.kennen.schoolairdrop.im.dao.GoodsDao;
import com.kennen.schoolairdrop.im.dao.GoodsUserDao;
import com.kennen.schoolairdrop.im.pojo.GoodsInfo;
import com.kennen.schoolairdrop.im.pojo.GoodsUserInfo;
import com.kennen.schoolairdrop.im.response.ResponseResult;
import com.kennen.schoolairdrop.im.service.IGoodsService;
import com.kennen.schoolairdrop.im.utils.MyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kennen
 * @date 2020/12/28 12:28
 */
@Slf4j
@Service
@Transactional
public class GoodsImpl implements IGoodsService {

    @Autowired
    private GoodsUserDao goodsUserDao;

    @Autowired
    private GoodsDao goodsDao;

    @Override
    public GoodsList getNearByGoods() {
        List<GoodsUserInfo> goodsInfos = goodsUserDao.findAllNearBy();
        log.info("物品获取成功");
        return getGoodsInfoList(goodsInfos);
    }

    @Override
    public GoodsList getMySelling(int userID) {
        List<GoodsUserInfo> goodsInfos = goodsUserDao.findAllByUserID(userID);
        log.info("在售物品获取成功");
        return getGoodsInfoList(goodsInfos);
    }

    @Override
    public GoodsDetail getGoodsDetail(int goodsID) {
        GoodsDetail detail = new GoodsDetail();
        GoodsUserInfo goodsUserInfo = goodsUserDao.findByID(goodsID);

        GoodsDetail.DataBean bean = new GoodsDetail.DataBean();
        bean.setGoods_description(goodsUserInfo.getGoods_description());
        bean.setGoods_img_set(goodsUserInfo.getGoods_img_set());
        bean.setSeller_img(goodsUserInfo.getUser_img_path());
        bean.setUid(goodsUserInfo.getGoods_sellerid());
        List<GoodsDetail.DataBean> list = new ArrayList<>();
        list.add(bean);

        detail.setSuccess(true);
        detail.setMessage("获取物品详细信息成功");
        detail.setData(list);
        log.info("物品详情获取成功");
        return detail;
    }

    @Override
    public GoodsList searchGoodsByKey(String keyword) {
        List<GoodsUserInfo> goodsInfos = goodsUserDao.findAll((Specification<GoodsUserInfo>)
                (root, criteriaQuery, criteriaBuilder) -> {
                    Predicate nameLike = criteriaBuilder.like(root.get("goods_name").as(String.class), "%" + keyword + "%");
                    Predicate descriptionLike = criteriaBuilder.like(root.get("goods_description").as(String.class), "%" + keyword + "%");
                    return criteriaBuilder.or(nameLike, descriptionLike);
                });

        log.info("搜索到" + goodsInfos.size() + "个相似物品");
        return getGoodsInfoList(goodsInfos);
    }

    /**
     * 拼装返回数据
     */
    private GoodsList getGoodsInfoList(List<GoodsUserInfo> goodsInfos) {
        GoodsList goodsList = new GoodsList();
        List<GoodsList.DataBean> list = new ArrayList<>();
        for (GoodsUserInfo goods : goodsInfos) {
            GoodsList.DataBean bean = new GoodsList.DataBean();
            bean.setGoods_id(goods.getGoods_id());
            bean.setGoods_img_cover(goods.getGoods_img_cover());
            bean.setGoods_is_brandNew(goods.getGoods_is_brandnew());
            bean.setGoods_is_quotable(goods.getGoods_is_quotable());
            bean.setGoods_name(goods.getGoods_name());
            bean.setGoods_price(goods.getGoods_price());
            bean.setSeller_info(goods.getUname());
            list.add(bean);
        }
        goodsList.setSuccess(true);
        goodsList.setData(list);

        return goodsList;
    }

    @Override
    public ResponseResult addNewGoods(String title,
                                      String cover,
                                      String picSet,
                                      String price,
                                      int isQuotable,
                                      int isSecondHand,
                                      String description) {

        createNewItem(title, cover, picSet, price, isQuotable, isSecondHand, description);
        log.info("物品添加成功");
        return ResponseResult.SUCCESS("物品添加成功").setData(new ArrayList<>());
    }

    private void createNewItem(String title, String cover, String picSet, String price, int isQuotable, int isSecondHand, String description) {
        GoodsInfo info = new GoodsInfo();
        info.setGoods_name(title);
        info.setGoods_img_cover(cover);
        info.setGoods_img_set(picSet);
        info.setGoods_price(price);
        info.setGoods_is_quotable(isQuotable);
        info.setGoods_is_brandnew(isSecondHand == 1 ? 0 : 1);
        info.setGoods_description(description);
        info.setGoods_sellerid(15);
        goodsDao.save(info);
    }

    @Override
    public ResponseResult deleteGoods(int goodsID) {
        GoodsInfo goods = goodsDao.findByID(goodsID);
        if (goods == null) return ResponseResult.FAILED("物品不存在").setData(new ArrayList<>());

        String cover = goods.getGoods_img_cover();
        File file = new File("D:/" + cover);
        log.info(file.getAbsolutePath());
        if (file.exists()) {
            file.delete();
        }

        List<String> picSet = MyUtil.getArrayFromString(goods.getGoods_img_set());
        for (String s : picSet) {
            File p = new File("D:/" + s);
            log.info(p.getAbsolutePath());
            if (p.exists()) {
                p.delete();
            }
        }

        try {
            goodsDao.delete(goods);
        } catch (Exception e) {
            return ResponseResult.FAILED("删除时发生错误").setData(new ArrayList<>());
        }
        log.info("id为" + goodsID + "的物品已删除");
        return ResponseResult.SUCCESS("删除成功").setData(new ArrayList<>());
    }
}
