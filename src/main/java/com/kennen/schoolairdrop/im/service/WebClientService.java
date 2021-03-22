package com.kennen.schoolairdrop.im.service;

import com.kennen.schoolairdrop.im.utils.Constants;
import com.kennen.schoolairdrop.im.utils.RSAUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @author kennen
 * @date 2021/2/23 17:04
 */

@Slf4j
@Service
public class WebClientService {

    public static final WebClient client = WebClient.builder()
            .baseUrl(Constants.LOCAL_BASE_URL)
            .build();

    /**
     * 发送推送通知
     */
    public void pushNotification(String userID, String content) {
        // WebClient 非阻塞式请求
        String encryptedUserID = RSAUtil.encryptWithPublicKey(Constants.PUSH_NOTIFICATION_PUBLI_KEY, userID);
        client.post()
                .uri("appapi/push/pushNotification")
                .body(BodyInserters.fromFormData("user_id", encryptedUserID).with("body", content))
                .retrieve()
                .bodyToMono(String.class)
                .block(Duration.ofMillis(2500L));
    }
}
