package com.kennen.schoolairdrop.im.pojo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "offline_from_all0")
@SecondaryTables({
        @SecondaryTable(name = "offline_from_all1"),
        @SecondaryTable(name = "offline_from_all2"),
        @SecondaryTable(name = "offline_from_all3"),
        @SecondaryTable(name = "offline_from_all4"),
})
public class OfflineFromAll {
    @Column(name = "sender_id")
    private String sender_id;

    @Column(name = "receiver_id")
    private String receiver_id;

    @Id
    @Column(name = "finger_print")
    private String finger_print;

    @Column(name = "message_type")
    private int message_type;

    @Column(name = "message")
    private String message;

    @Column(name = "send_time")
    private long send_time;


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


    public String getFinger_print() {
        return finger_print;
    }

    public void setFinger_print(String finger_print) {
        this.finger_print = finger_print;
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


    public long getSend_time() {
        return send_time;
    }

    public void setSend_time(long send_time) {
        this.send_time = send_time;
    }

}
