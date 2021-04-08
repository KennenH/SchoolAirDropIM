package com.kennen.schoolairdrop.im.service.impl;

import com.kennen.schoolairdrop.im.bean.ProtocalWithTime;
import com.kennen.schoolairdrop.im.response.ResponseResult;
import com.kennen.schoolairdrop.im.service.IMessageService;
import com.kennen.schoolairdrop.im.service.IOfflineService;
import com.kennen.schoolairdrop.im.utils.Constants;
import net.x52im.mobileimsdk.server.processor.OnlineProcessor;
import net.x52im.mobileimsdk.server.protocal.Protocal;
import net.x52im.mobileimsdk.server.utils.LocalSendHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MessageImpl implements IMessageService {

    @Autowired
    private IOfflineService offlineService;

    @Override
    public ResponseResult logout(String targetID, String message) {
        Protocal protocal = new Protocal(
                0, message,
                Constants.SERVER_ID, targetID,
                true, null, Constants.MESSAGE_TYPE_LOGOUT);
        if (OnlineProcessor.isOnline(targetID)) {
            try {
                LocalSendHelper.sendData(protocal, (success, extraObj) -> {
                    if (!success) {
                        // 对方在线但由于某种原因没有发送成功
                        offlineService.saveOfflineMessage(new ProtocalWithTime(protocal));
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseResult.FAILED("发送服务级消息时出错");
            }
            return ResponseResult.SUCCESS("服务级消息发送成功");
        } else {
            if (offlineService.saveOfflineMessage(new ProtocalWithTime(protocal))) {
                return ResponseResult.SUCCESS("服务级消息离线保存成功");
            } else {
                return ResponseResult.FAILED("服务级消息离线保存失败");
            }
        }
    }
}
