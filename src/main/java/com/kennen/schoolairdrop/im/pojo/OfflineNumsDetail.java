package com.kennen.schoolairdrop.im.pojo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "offline_nums_detail_a0")
@IdClass(OfflineNumDetailPK.class)
@SecondaryTables({
        @SecondaryTable(name = "offline_nums_detail_a1"),
        @SecondaryTable(name = "offline_nums_detail_a2"),
        @SecondaryTable(name = "offline_nums_detail_a3"),
        @SecondaryTable(name = "offline_nums_detail_a4"),
        @SecondaryTable(name = "offline_nums_detail_b0"),
        @SecondaryTable(name = "offline_nums_detail_b1"),
        @SecondaryTable(name = "offline_nums_detail_b2"),
        @SecondaryTable(name = "offline_nums_detail_b3"),
        @SecondaryTable(name = "offline_nums_detail_b4")
})
public class OfflineNumsDetail {

    @Id
    @Column(name = "sender_id")
    private String sender_id;

    @Id
    @Column(name = "receiver_id")
    private String receiver_id;

    @Column(name = "offline_num")
    private int offline_num;

    @Column(name = "message_type")
    private int message_type;

    @Column(name = "message")
    private String message;

    @Column(name = "send_time")
    private Date send_time;


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


    public int getOffline_num() {
        return offline_num;
    }

    public void setOffline_num(int offline_num) {
        this.offline_num = offline_num;
    }


    public int getMessage_type() {
        return message_type;
    }

    public void setMessage_type(int message_type) {
        this.message_type = message_type;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Date getSend_time() {
        return send_time;
    }

    public void setSend_time(Date send_time) {
        this.send_time = send_time;
    }

}
