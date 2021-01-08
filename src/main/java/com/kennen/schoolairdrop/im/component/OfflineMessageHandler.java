package com.kennen.schoolairdrop.im.component;

import com.kennen.schoolairdrop.im.bean.ProtocalWithTime;
import com.kennen.schoolairdrop.im.service.IOfflineService;
import lombok.extern.slf4j.Slf4j;
import net.x52im.mobileimsdk.server.protocal.Protocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author kennen
 * @date 2020/12/13 15:32
 */

@Slf4j
@Component
public class OfflineMessageHandler {

    /**
     * 处理离线消息
     *
     * @param protocalWithTime 消息本体
     */
    public boolean handleOfflineMessage(ProtocalWithTime protocalWithTime) {
        return offlineMessageHandler.offlineService.saveOfflineMessage(protocalWithTime);
    }

    private static OfflineMessageHandler offlineMessageHandler;

    @Autowired
    private IOfflineService offlineService;

    @PostConstruct
    public void init() {
        offlineMessageHandler = this;
    }

}
