package com.kennen.schoolairdrop.im.component;

import com.kennen.schoolairdrop.im.service.IUserVerifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author kennen
 * @date 2020/12/13 19:35
 */
@Slf4j
@Component
public class UserVerifyHandler {

    /**
     * 验证用户身份
     *
     * @param userID 用户id
     * @param token  验证信息
     * @return 验证是否通过
     */
    public boolean verifyUser(String userID, String token) {
        return userVerifyHandler.userVerifyService.verifyUser(userID, token);
    }

    private static UserVerifyHandler userVerifyHandler;

    @Autowired
    private IUserVerifyService userVerifyService;

    @PostConstruct
    public void init() {
        userVerifyHandler = this;
    }
}
