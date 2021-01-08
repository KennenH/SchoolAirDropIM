package com.kennen.schoolairdrop.im.controller;

import com.kennen.schoolairdrop.im.response.ResponseResult;
import com.kennen.schoolairdrop.im.service.IOfflineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/im/offline")
public class OfflineController {

    @Autowired
    private IOfflineService offlineService;

    /**
     * 获取用户在离线时接收到的来自特定用户的消息
     *
     * @param token    登录者用户token
     * @param senderID 消息发送者userID
     */
    @PostMapping("/messages")
    public ResponseResult getOfflineMessageByID(@RequestHeader("Authorization") String token, @RequestParam("sender_id") String senderID) {
        return offlineService.getOfflineMessageByID(token, senderID);
    }

    @PostMapping("/ack")
    public ResponseResult offlineMessageAck(@RequestHeader("Authorization") String token, @RequestParam("fingerprints") List<String> fingerprints) {
        return offlineService.offlineMessageAck(token, fingerprints);
    }

    @PostMapping("/offline_nums")
    public ResponseResult getOfflineNums(@RequestHeader("Authorization") String token) {
        return offlineService.getOfflineNums(token);
    }


}
