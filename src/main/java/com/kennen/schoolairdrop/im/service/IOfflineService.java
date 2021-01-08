package com.kennen.schoolairdrop.im.service;

import com.kennen.schoolairdrop.im.bean.ProtocalWithTime;
import com.kennen.schoolairdrop.im.response.ResponseResult;

import java.util.List;

public interface IOfflineService {

    /**
     * 获取来自senderID的消息具体内容
     * <p>
     * 上层应用应在进入与某人的聊天界面时再拉取针对该用户发来的消息内容
     *
     * @param token    接收者token
     * @param senderID 消息发送者
     */
    ResponseResult getOfflineMessageByID(String token, String senderID);

    /**
     * 用户接收到离线消息的ack包，是具体到每一条消息的ack
     *
     * @param token        用户token
     * @param fingerprints 收到的离线消息指纹集合
     */
    ResponseResult offlineMessageAck(String token, List<String> fingerprints);

    /**
     * 获取来自所有独特的用户的消息个数和每个用户的最新消息与时间戳
     * <p>
     * 上层应用在用户登录成功后调用该方法
     *
     * @param token 接收者token
     */
    ResponseResult getOfflineNums(String token);

    /**
     * 存储未被在线收到的消息，等待接收者主动拉取
     *
     * @param protocalWithTime 消息
     * @return 离线消息是否已经正确存储
     */
    boolean saveOfflineMessage(ProtocalWithTime protocalWithTime);

    /**
     * 与某个特定用户离线消息已读ack，非离线消息本身ack
     */
    ResponseResult offlineNumsAck(String receiverID, String senderID);
}