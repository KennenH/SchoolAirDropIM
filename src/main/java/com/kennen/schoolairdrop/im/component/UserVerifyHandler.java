package com.kennen.schoolairdrop.im.component;

import com.kennen.schoolairdrop.im.service.IUserVerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author kennen
 * @date 2020/12/13 19:35
 */

@Component
public class UserVerifyHandler {

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
