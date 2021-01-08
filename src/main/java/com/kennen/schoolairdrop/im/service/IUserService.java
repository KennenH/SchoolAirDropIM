package com.kennen.schoolairdrop.im.service;

import com.kennen.schoolairdrop.im.bean.UpdateAvatar;
import com.kennen.schoolairdrop.im.bean.UserBean;
import com.kennen.schoolairdrop.im.response.ResponseResult;

/**
 * @author kennen
 * @date 2020/12/29 16:50
 */
public interface IUserService {

    UserBean getUserInfoByID(int userID);

    ResponseResult updateUserName(String name, int userID);

    UpdateAvatar updateAvatar(String photo, int uid);
}
