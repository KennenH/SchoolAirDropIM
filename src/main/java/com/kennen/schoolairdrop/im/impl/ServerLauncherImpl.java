package com.kennen.schoolairdrop.im.impl;

import lombok.extern.slf4j.Slf4j;
import net.x52im.mobileimsdk.server.ServerLauncher;
import net.x52im.mobileimsdk.server.network.Gateway;
import net.x52im.mobileimsdk.server.network.GatewayTCP;
import net.x52im.mobileimsdk.server.network.GatewayUDP;
import net.x52im.mobileimsdk.server.qos.QoS4ReciveDaemonC2S;
import net.x52im.mobileimsdk.server.qos.QoS4SendDaemonS2C;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
public class ServerLauncherImpl extends ServerLauncher {

    public ServerLauncherImpl() throws IOException {
    }

    /**
     * 静态类方法：进行一些全局配置设置。
     */
    static {
        // 设置MobileIMSDK服务端的UDP网络监听端口
//        GatewayUDP.PORT = 7901;
        // 设置MobileIMSDK服务端的TCP网络监听端口
        GatewayTCP.PORT = 8901;

        // 设置MobileIMSDK服务端仅支持UDP协议
//		ServerLauncher.supportedGateways = Gateway.SUPPORT_UDP;
        // 设置MobileIMSDK服务端仅支持TCP协议
		ServerLauncher.supportedGateways = Gateway.SUPPORT_TCP;
        // 设置MobileIMSDK服务端同时支持UDP、TCP两种协议
//        ServerLauncher.supportedGateways = Gateway.SUPPORT_UDP | Gateway.SUPPORT_TCP;

        // 开/关Demog日志的输出
        QoS4SendDaemonS2C.getInstance().setDebugable(false);
        QoS4ReciveDaemonC2S.getInstance().setDebugable(false);

        // 与客户端协商一致的心跳频率模式设置
//		ServerToolKits.setSenseModeUDP(SenseModeUDP.MODE_15S);
//		ServerToolKits.setSenseModeTCP(SenseModeTCP.MODE_15S);

        // 关闭与Web端的消息互通桥接器（其实SDK中默认就是false）
        ServerLauncher.bridgeEnabled = false;
//         TODO 跨服桥接器MQ的URI（本参数只在ServerLauncher.bridgeEnabled为true时有意义）
//		BridgeProcessor.IMMQ_URI = "amqp://js:19844713@192.168.0.190";

        // 设置最大TCP帧内容长度（不设置则默认最大是 6 * 1024字节）
//		GatewayTCP.TCP_FRAME_MAX_BODY_LENGTH = 60 * 1024;
    }

    @Override
    protected void initListeners() {
        this.setServerEventListener(new ServerEventListenerImpl());
        this.setServerMessageQoSEventListener(new MessageQoSEventS2CListenerImpl());
    }


}
