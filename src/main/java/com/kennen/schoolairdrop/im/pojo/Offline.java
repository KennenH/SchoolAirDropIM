package com.kennen.schoolairdrop.im.pojo;

import javax.persistence.*;

/**
 * @author kennen
 * @date 2020/12/15 15:58
 */
@Entity
@Table(name = "offline0")
@SecondaryTables({
        @SecondaryTable(name = "offline1"),
        @SecondaryTable(name = "offline2"),
        @SecondaryTable(name = "offline3"),
        @SecondaryTable(name = "offline4"),
})
public class Offline {

    @Id
    @Column(name = "offline_id")
    private int offline_id;

    @Column(name = "finger_print")
    private String finger_print;

    @Column(name = "sender_id")
    private String sender_id;

    @Column(name = "receiver_id")
    private String receiver_id;

    @Column(name = "message")
    private String message;

    @Column(name = "message_type")
    private int message_type;

    @Column(name = "send_time")
    private long send_time;

    @Column(name = "received")
    private int received;

    public int getOffline_id() {
        return offline_id;
    }

    public void setOffline_id(int offlineID) {
        this.offline_id = offlineID;
    }

    public String getFinger_print() {
        return finger_print;
    }

    public void setFinger_print(String finger_print) {
        this.finger_print = finger_print;
    }

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getMessage_type() {
        return message_type;
    }

    public void setMessage_type(int message_type) {
        this.message_type = message_type;
    }

    public long getSend_time() {
        return send_time;
    }

    public void setSend_time(long send_time) {
        this.send_time = send_time;
    }

    public int getReceived() {
        return received;
    }

    public void setReceived(int received) {
        this.received = received;
    }
}
