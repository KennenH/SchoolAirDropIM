package com.kennen.schoolairdrop.im.service;

import com.kennen.schoolairdrop.im.response.ResponseResult;

/**
 * 服务端向前端发送的im消息
 * <p>
 * 所有本服务中的方法都不应该允许非本机访问
 */
public interface IMessageService {

    /**
     * 有服务端向前端发送的退出登录请求
     *
     * @param targetID 目标用户id
     * @param message  向用户展示的退出原因
     */
    ResponseResult logout(String targetID, String message);
}
