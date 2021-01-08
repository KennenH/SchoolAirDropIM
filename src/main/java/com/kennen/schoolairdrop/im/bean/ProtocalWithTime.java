package com.kennen.schoolairdrop.im.bean;

import com.kennen.schoolairdrop.im.pojo.Offline;
import net.x52im.mobileimsdk.server.protocal.Protocal;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @author kennen
 * @date 2020/12/13 18:52
 */
public class ProtocalWithTime implements Serializable {

    public ProtocalWithTime(Protocal protocal) {
        BeanUtils.copyProperties(protocal, this);
        this.sendTime = new Date();
        received = 0;
    }

    public ProtocalWithTime(Offline offline) {
        sendTime = offline.getSendTime();
        typeu = offline.getMessageType();
        dataContent = offline.getMessage();
        from = offline.getSenderID();
        to = offline.getReceiverID();
        fp = offline.getFingerPrint();
        received = offline.getReceived();
    }

    private Date sendTime;

    private int typeu;

    private String dataContent;

    private String from;

    private String to;

    private String fp;

    /**
     * 0 表示未收到
     * 1 表示已经收到
     */
    private int received;

    public int isReceived() {
        return received;
    }

    public void setReceived(int received) {
        this.received = received;
    }

    public int getTypeu() {
        return typeu;
    }

    public void setTypeu(int typeu) {
        this.typeu = typeu;
    }

    public String getDataContent() {
        return dataContent;
    }

    public void setDataContent(String dataContent) {
        this.dataContent = dataContent;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFp() {
        return fp;
    }

    public void setFp(String fp) {
        this.fp = fp;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
