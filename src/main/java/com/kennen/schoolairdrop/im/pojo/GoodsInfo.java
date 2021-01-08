package com.kennen.schoolairdrop.im.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "goods_info")
public class GoodsInfo {

    @Id
    @Column(name = "goods_id")
    private int goods_id;

    @Column(name = "goods_img_cover")
    private String goods_img_cover;

    @Column(name = "goods_img_set")
    private String goods_img_set;

    @Column(name = "goods_name")
    private String goods_name;

    @Column(name = "goods_price")
    private String goods_price;

    @Column(name = "goods_latitude")
    private double goods_latitude;

    @Column(name = "goods_longitude")
    private double goods_longitude;

    @Column(name = "goods_is_quotable")
    private int goods_is_quotable;

    @Column(name = "goods_is_brandnew")
    private int goods_is_brandnew;

    @Column(name = "goods_description")
    private String goods_description;

    @Column(name = "goods_sellerid")
    private int goods_sellerid;

    @Column(name = "watch_count")
    private int watch_count;

    @Column(name = "favor_count")
    private int favor_count;

    @Column(name = "chat_count")
    private int chat_count;


    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }


    public String getGoods_img_cover() {
        return goods_img_cover;
    }

    public void setGoods_img_cover(String goods_img_cover) {
        this.goods_img_cover = goods_img_cover;
    }


    public String getGoods_img_set() {
        return goods_img_set;
    }

    public void setGoods_img_set(String goods_img_set) {
        this.goods_img_set = goods_img_set;
    }


    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }


    public String  getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }


    public double getGoods_latitude() {
        return goods_latitude;
    }

    public void setGoods_latitude(double goods_latitude) {
        this.goods_latitude = goods_latitude;
    }


    public double getGoods_intitude() {
        return goods_longitude;
    }

    public void setGoods_intitude(double goods_intitude) {
        this.goods_longitude = goods_intitude;
    }


    public int getGoods_is_quotable() {
        return goods_is_quotable;
    }

    public void setGoods_is_quotable(int goods_is_quotable) {
        this.goods_is_quotable = goods_is_quotable;
    }


    public int getGoods_is_brandnew() {
        return goods_is_brandnew;
    }

    public void setGoods_is_brandnew(int goods_is_brandnew) {
        this.goods_is_brandnew = goods_is_brandnew;
    }


    public String getGoods_description() {
        return goods_description;
    }

    public void setGoods_description(String goods_description) {
        this.goods_description = goods_description;
    }


    public int getGoods_sellerid() {
        return goods_sellerid;
    }

    public void setGoods_sellerid(int goods_sellerid) {
        this.goods_sellerid = goods_sellerid;
    }


    public int getWatch_count() {
        return watch_count;
    }

    public void setWatch_count(int watch_count) {
        this.watch_count = watch_count;
    }


    public int getFavor_count() {
        return favor_count;
    }

    public void setFavor_count(int favor_count) {
        this.favor_count = favor_count;
    }


    public int getChat_count() {
        return chat_count;
    }

    public void setChat_count(int chat_count) {
        this.chat_count = chat_count;
    }

}
