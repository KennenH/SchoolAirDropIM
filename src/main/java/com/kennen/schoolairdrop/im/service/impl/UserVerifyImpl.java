package com.kennen.schoolairdrop.im.service.impl;

import com.kennen.schoolairdrop.im.dao.AccessTokenDao;
import com.kennen.schoolairdrop.im.pojo.AccessToken;
import com.kennen.schoolairdrop.im.response.ResponseResult;
import com.kennen.schoolairdrop.im.service.IUserVerifyService;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author kennen
 * @date 2020/12/13 19:38
 */

@Slf4j
@Service
@Transactional
public class UserVerifyImpl implements IUserVerifyService {

    @Autowired
    private AccessTokenDao accessTokenDao;

    @Override
    public boolean verifyUser(String userID, String token) {
        final AccessToken accessToken = accessTokenDao.findOneByAccessToken(token);
        if (accessToken == null) {
            return false;
        }
        return userID.equals(String.valueOf(accessToken.getUserID()));
    }
}
