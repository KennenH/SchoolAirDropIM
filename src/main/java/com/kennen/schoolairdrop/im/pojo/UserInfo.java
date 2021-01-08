package com.kennen.schoolairdrop.im.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "user_info")
public class UserInfo {

    @Id
    @Column(name = "uid")
    private int uid;

    @Column(name = "uname")
    private String uname;

    @Column(name = "ugender")
    private String ugender;

    @Column(name = "user_img_path")
    private String user_img_path;

    @Column(name = "ualipay")
    private String ualipay;

    @Column(name = "uphone")
    private String uphone;

    @Column(name = "device_token")
    private String device_token;

    @Column(name = "count_on_sale")
    private long count_on_sale;

    @Column(name = "registration_id")
    private String registration_id;


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }


    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }


    public String getUgender() {
        return ugender;
    }

    public void setUgender(String ugender) {
        this.ugender = ugender;
    }


    public String getUser_img_path() {
        return user_img_path;
    }

    public void setUser_img_path(String user_img_path) {
        this.user_img_path = user_img_path;
    }


    public String getUalipay() {
        return ualipay;
    }

    public void setUalipay(String ualipay) {
        this.ualipay = ualipay;
    }


    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }


    public String getDevice_token() {
        return device_token;
    }

    public void setDevice_token(String device_token) {
        this.device_token = device_token;
    }


    public long getCount_on_sale() {
        return count_on_sale;
    }

    public void setCount_on_sale(long count_on_sale) {
        this.count_on_sale = count_on_sale;
    }


    public String getRegistration_id() {
        return registration_id;
    }

    public void setRegistration_id(String registration_id) {
        this.registration_id = registration_id;
    }

}
