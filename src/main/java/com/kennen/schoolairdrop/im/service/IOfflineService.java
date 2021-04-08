package com.kennen.schoolairdrop.im.service;

import com.kennen.schoolairdrop.im.bean.ProtocalWithTime;
import com.kennen.schoolairdrop.im.response.ResponseResult;
import org.springframework.web.servlet.handler.RequestMatchResult;

import java.util.List;

public interface IOfflineService {

    /**
     * 二次拉取
     * 拉取时间后的10条消息并ack上次拉取的消息集合
     * <p>
     * 上层应用在查看获取到的最新10条记录后若继续查看则需要调用该方法
     *
     * @param token     接收者token，即调用该方法的用户
     * @param senderID  消息发送者
     * @param startTime 想要拉取消息的临界消息时间，即从这个时间前去找
     */
    ResponseResult getOfflineBefore(String token, String senderID, long startTime);

    /**
     * ack消息
     * <p>
     * 该接口在app调用{@link IOfflineService#getOfflineBefore(String, String, long)}
     * 预拉取到的下一批消息数量小于15时调用
     *
     * @param token        消息接收者token，即本方法调用者
     * @param startTime 该批消息的最早一批消息
     */
    ResponseResult ackOffline(String token, String senderID, long startTime);

    /**
     * 获取来自所有独特的用户的消息个数和每个用户的最新消息与时间戳
     * <p>
     * 上层应用在用户登录成功后调用该方法
     * 每有一条发送至接收者的离线消息，就会将接收者未读消息自增1
     *
     * @param token 接收者token
     */
    ResponseResult getOfflineNum(String token);

    /**
     * 存储未被在线收到的消息，等待接收者主动拉取
     * <p>
     * 上层应用无需额外做任何动作
     * 服务器需要在离线回调中将消息存放至redis中，适时将其转移
     * 至数据库中
     *
     * @param protocalWithTime 消息
     * @return 离线消息是否已经正确存储
     */
    boolean saveOfflineMessage(ProtocalWithTime protocalWithTime);
}