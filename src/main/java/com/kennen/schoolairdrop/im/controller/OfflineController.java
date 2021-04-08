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
     * @param token     登录者用户token
     * @param senderID  消息发送者userID
     * @param startTime 临界消息时间
     *                  若有，则代表需要拉取该消息之前发的消息
     *                  若无，则代表需要拉取最新的消息
     */
    @PostMapping("/pull")
    public ResponseResult pullOfflineByID(
            @RequestHeader(
                    value = "Authorization",
                    required = false) String token,
            @RequestParam(
                    value = "sender_id",
                    required = false) String senderID,
            @RequestParam(
                    value = "start",
                    required = false) long startTime) {
        if (token == null) {
            return ResponseResult.FAILED("token not present");
        } else if (senderID == null) {
            return ResponseResult.FAILED("sender id not present");
        } else if (startTime == 0) {
            return ResponseResult.FAILED("start time not present");
        }
        return offlineService.getOfflineBefore(token, senderID, startTime);
    }

    /**
     * 获取离线消息数量，进入app时调用
     * <p>
     * 实际获取的数据包含：
     * ①来自每个给我发送了消息的用户的离线消息数量以及最后一条消息指纹
     * ②来自每个这些用户的最新10条消息
     * <p>
     * 这样便可以使用最后一条消息的指纹获取最后一条消息的内容和时间戳
     */
    @PostMapping("/num")
    public ResponseResult getOfflineNum(
            @RequestHeader(
                    value = "Authorization",
                    required = false) String token) {
        if (token == null) {
            return ResponseResult.FAILED("token not present");
        }
        return offlineService.getOfflineNum(token);
    }

    /**
     * ack给定时间以及之前的消息
     */
    @PostMapping("/ack")
    public ResponseResult ackOffline(
            @RequestHeader(
                    value = "Authorization",
                    required = false) String token,
            @RequestParam(
                    value = "sender_id",
                    required = false) String senderID,
            @RequestParam(
                    value = "start",
                    required = false) long startTime) {
        if (token == null) {
            return ResponseResult.FAILED("token not present");
        } else if (senderID == null) {
            return ResponseResult.FAILED("sender id not present");
        } else if (startTime == 0) {
            return ResponseResult.FAILED("start time not present");
        }
        return offlineService.ackOffline(token, senderID, startTime);
    }
}
