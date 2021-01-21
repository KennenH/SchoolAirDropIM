package com.kennen.schoolairdrop.im.pojo;

import java.io.Serializable;

/**
 * @author kennen
 * @date 2021/1/20 19:54
 */
public class OfflineNumDetailPK implements Serializable {

    private String sender_id;

    private String receiver_id;

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    public String getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(String receiver_id) {
        this.receiver_id = receiver_id;
    }
}

