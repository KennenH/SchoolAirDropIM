package com.kennen.schoolairdrop.im.service.impl;

import com.kennen.schoolairdrop.im.dao.AccessTokenDao;
import com.kennen.schoolairdrop.im.pojo.AccessToken;
import com.kennen.schoolairdrop.im.response.ResponseResult;
import com.kennen.schoolairdrop.im.service.IUserVerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author kennen
 * @date 2020/12/13 19:38
 */

@Service
@Transactional
public class UserVerifyImpl implements IUserVerifyService {

    @Autowired
    private AccessTokenDao accessTokenDao;

    @Override
    public boolean verifyUser(String userID, String token) {
        final AccessToken accessToken = accessTokenDao.findOneByAccessToken(token);
        // 用户token存在
        return userID.equals(accessToken.getUserID());
    }

    @Override
    public List<AccessToken> test() {
        return accessTokenDao.findAll();
    }


}
