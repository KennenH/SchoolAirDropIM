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
     * @param token             登录者用户token
     * @param senderID          消息发送者userID
     * @param fingerprintLatest 临界消息
     *                          若有，则代表需要拉取该消息后的消息
     *                          若无，则代表需要拉取最新的消息
     * @param fingerprintsToAck 需要ack的消息数组
     */
    @PostMapping("/pull")
    public ResponseResult pullOfflineByID(@RequestHeader("Authorization") String token,
                                          @RequestParam("sender_id") String senderID,
                                          @RequestParam("latest_fingerprint") String fingerprintLatest,
                                          @RequestParam(
                                                  name = "ack_fingerprints",
                                                  required = false) List<String> fingerprintsToAck) {
        return offlineService.getOfflineSecondaryPull(token, senderID, fingerprintLatest, fingerprintsToAck);
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
    public ResponseResult getOfflineNum(@RequestHeader("Authorization") String token) {
        return offlineService.getOfflineNum(token);
    }


}
