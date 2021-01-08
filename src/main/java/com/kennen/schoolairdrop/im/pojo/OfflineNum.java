package com.kennen.schoolairdrop.im.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author kennen
 * @date 2020/12/15 13:15
 */

@Entity
@Table(name = "offline_nums")
public class OfflineNum {

    @Id
    private String fingerPrint;

    @Column(name = "sender_id")
    private int from;

    @Column(name = "receiver_id")
    private int to;

    @Column(name = "offline_num")
    private int offlineNums;

    @Column(name = "last_message_content")
    private String content;

    @Column(name = "time_stamp")
    private Date timeStamp;

    public String getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getOfflineNums() {
        return offlineNums;
    }

    public void setOfflineNums(int offlineNums) {
        this.offlineNums = offlineNums;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
