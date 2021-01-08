package com.kennen.schoolairdrop.im.pojo;

import javax.persistence.*;
import java.util.Date;

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
        @SecondaryTable(name = "offline5"),
        @SecondaryTable(name = "offline6"),
        @SecondaryTable(name = "offline7"),
        @SecondaryTable(name = "offline8"),
        @SecondaryTable(name = "offline9")
})
public class Offline {

    @Id
    @Column(name = "finger_print")
    private String fingerPrint;

    @Column(name = "sender_id")
    private String senderID;

    @Column(name = "receiver_id")
    private String receiverID;

    @Column(name = "message")
    private String message;

    @Column(name = "message_type")
    private int messageType;

    @Column(name = "send_time")
    private Date sendTime;

    @Column(name = "received")
    private int received;

    public String getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(String finger_print) {
        this.fingerPrint = finger_print;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String sender_id) {
        this.senderID = sender_id;
    }

    public String getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(String receiver_id) {
        this.receiverID = receiver_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int message_type) {
        this.messageType = message_type;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date send_time) {
        this.sendTime = send_time;
    }

    public int getReceived() {
        return received;
    }

    public void setReceived(int received) {
        this.received = received;
    }
}
