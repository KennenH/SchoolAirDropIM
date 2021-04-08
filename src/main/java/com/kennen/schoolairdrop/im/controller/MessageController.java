package com.kennen.schoolairdrop.im.controller;

import com.kennen.schoolairdrop.im.response.ResponseResult;
import com.kennen.schoolairdrop.im.service.IMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务级消息，任何时候都不应该允许非本机访问
 */
@Slf4j
@RestController
@RequestMapping("/im/message")
public class MessageController {

    @Autowired
    private IMessageService messageService;

    /**
     * 服务级退出登录消息
     * 前端在登录状态下收到消息需要弹窗提示并退出登录
     *
     * @param message 操作时需要向用户展示的信息提示
     */
    @RequestMapping("/logout")
    public ResponseResult logout(
            @RequestParam("target_id") String userID,
            @RequestParam("message") String message) {
        return messageService.logout(userID, message);
    }

}
