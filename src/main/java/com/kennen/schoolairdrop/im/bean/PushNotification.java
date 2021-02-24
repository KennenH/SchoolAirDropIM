package com.kennen.schoolairdrop.im.bean;

import java.io.Serializable;

/**
 * @author kennen
 * @date 2021/2/23 16:58
 */
public class PushNotification implements Serializable {

    @Override
    public String toString() {
        return "Authorize{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
