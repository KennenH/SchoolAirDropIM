package com.kennen.schoolairdrop.im.service;

import com.kennen.schoolairdrop.im.bean.PushNotification;
import com.kennen.schoolairdrop.im.utils.Constants;
import com.kennen.schoolairdrop.im.utils.RSAUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author kennen
 * @date 2021/2/23 17:04
 */

@Slf4j
@Service
public class RestTemplateService {

    private final RestTemplate restTemplate;

    /**
     * 手机通知推送接口
     */
    private static final String PUSH_NOTIFICATION = Constants.LOCAL_BASE_URL + "appapi/push/pushNotification";

    public RestTemplateService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    /**
     * 验证并推送通知
     */
    public void pushNotification(String userID, String content) {
        // 组装headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 组装请求参数
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        String encryptedUserID = RSAUtil.encryptWithPublicKey(Constants.PUSH_NOTIFICATION_PUBLI_KEY, userID);
        map.add("user_id", encryptedUserID);
        map.add("body", content);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        // 发起验证推送请求
        restTemplate.postForEntity(PUSH_NOTIFICATION, request, PushNotification.class);
    }
}
