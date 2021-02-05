package com.kennen.schoolairdrop.im.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import java.util.Date;

@Entity
@Table(name = "user_info")
public class UserInfo {

    @Id
    @Column(name = "user_id")
    private int user_id;

    @Column(name = "user_name")
    private String user_name;

    @Column(name = "user_avatar")
    private String user_avatar;

    @Column(name = "last_login_time")
    private Date last_login_time;

    @Column(name = "last_login_address")
    private String last_login_address;

    @Column(name = "createtime")
    private int createtime;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int uid) {
        this.user_id = uid;
    }


    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String uname) {
        this.user_name = uname;
    }


    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String user_img_path) {
        this.user_avatar = user_img_path;
    }

    public Date getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(Date last_login_time) {
        this.last_login_time = last_login_time;
    }

    public String getLast_login_address() {
        return last_login_address;
    }

    public void setLast_login_address(String last_login_address) {
        this.last_login_address = last_login_address;
    }

    public int getCreatetime() {
        return createtime;
    }

    public void setCreatetime(int createtime) {
        this.createtime = createtime;
    }
}
