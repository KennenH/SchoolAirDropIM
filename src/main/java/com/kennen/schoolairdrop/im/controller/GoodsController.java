package com.kennen.schoolairdrop.im.controller;

import com.kennen.schoolairdrop.im.bean.GoodsDetail;
import com.kennen.schoolairdrop.im.bean.GoodsList;
import com.kennen.schoolairdrop.im.response.ResponseResult;
import com.kennen.schoolairdrop.im.service.IGoodsService;
import com.kennen.schoolairdrop.im.utils.Constants;
import com.kennen.schoolairdrop.im.utils.IDGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author kennen
 * @date 2020/12/28 12:19
 */
@Slf4j
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IDGenerator idGenerator;

    @Autowired
    private IGoodsService goodsService;

    @PostMapping("/getNearByGoods")
    public GoodsList getNearByGoods() {
        return goodsService.getNearByGoods();
    }

    @PostMapping("/getGoodsOnSale")
    public GoodsList getMySelling(@RequestParam("user_id") int userID) {
        return goodsService.getMySelling(userID);
    }

    @PostMapping("/getInfo")
    public GoodsDetail getGoodsDetail(@RequestParam("goods_id") int goodsID) {
        return goodsService.getGoodsDetail(goodsID);
    }

    @PostMapping("/searchGoods")
    public GoodsList searchGoodsByKey(@RequestParam("keyWords") String key) {
        return goodsService.searchGoodsByKey(key);
    }

    @PostMapping("/upload")
    public ResponseResult addNewGoods(HttpServletRequest request) {
        MultipartHttpServletRequest servletRequest = (MultipartHttpServletRequest) request;
        String title = servletRequest.getParameter("goods_name");
        String price = servletRequest.getParameter("goods_price");
        String isQuotable = servletRequest.getParameter("goods_is_quotable");
        String isBrandNew = servletRequest.getParameter("goods_is_brandNew");
        String description = servletRequest.getParameter("goods_description");
        MultipartFile cover = servletRequest.getFile("goods_img_cover");
        List<MultipartFile> picSet = servletRequest.getFiles("goods_img_set");

        return createImages(title, cover, picSet, price, isQuotable, isBrandNew, description);
    }

    @PostMapping("/deleteGoods")
    public ResponseResult deleteGoodsByID(@RequestParam("goods_id") int goodsID) {
        return goodsService.deleteGoods(goodsID);
    }

    /**
     * 创建随机命名的图片并保存
     */
    private ResponseResult createImages(String title,
                                        MultipartFile cover,
                                        List<MultipartFile> picSet,
                                        String price,
                                        String isQuotable,
                                        String isBrandNew,
                                        String description) {

        if (picSet == null || cover == null) {
            log.info("图片为空，上传出错");
            return ResponseResult.FAILED("图片为空，上传出错");
        }

        String coverType = cover.getContentType();
        if (!"image/jpg".equals(coverType) &&
                !"image/jpeg".equals(coverType) &&
                !"image/png".equals(coverType) &&
                !"image/*".equals(coverType)) {
            log.info("图片格式错误");
            return ResponseResult.FAILED("图片格式错误");
        }

        for (MultipartFile file : picSet) {
            String fileType = file.getContentType();
            if (!"image/jpg".equals(fileType) &&
                    !"image/jpeg".equals(fileType) &&
                    !"image/png".equals(fileType) &&
                    !"image/*".equals(fileType)) {
                log.info("图片格式错误");
                return ResponseResult.FAILED("图片格式错误");
            }
        }

        String coverPath = Constants.IMAGE_DIR + idGenerator.nextId() + Constants.IMAGE_POST_FIX;
        StringBuilder picSetPath = new StringBuilder();
        File coverTarget = new File(coverPath);
        try {
            cover.transferTo(coverTarget);
        } catch (IOException e) {
            log.info("上传图片失败");
            return ResponseResult.FAILED("上传图片失败" + e.getMessage());
        }

        for (MultipartFile file : picSet) {
            String path = Constants.IMAGE_DIR + idGenerator.nextId() + Constants.IMAGE_POST_FIX;
            picSetPath.append(path.substring(3)).append('&');
            File picSetTarget = new File(path);
            try {
                file.transferTo(picSetTarget);
            } catch (IOException e) {
                log.info("上传图片失败");
                return ResponseResult.FAILED("上传图片失败" + e.getMessage());
            }
        }
        picSetPath.deleteCharAt(picSetPath.length() - 1);

        return goodsService.addNewGoods(title,
                coverPath.substring(3),
                picSetPath.toString(),
                price,
                "0".equals(isQuotable) ? 0 : 1,
                "0".endsWith(isBrandNew) ? 1 : 0,
                description);
    }
}
