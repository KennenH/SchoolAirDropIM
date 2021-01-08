package com.kennen.schoolairdrop.im.service.impl;

import com.kennen.schoolairdrop.im.bean.UpdateAvatar;
import com.kennen.schoolairdrop.im.bean.UserBean;
import com.kennen.schoolairdrop.im.dao.UserDao;
import com.kennen.schoolairdrop.im.pojo.UserInfo;
import com.kennen.schoolairdrop.im.response.ResponseResult;
import com.kennen.schoolairdrop.im.service.IUserService;
import com.kennen.schoolairdrop.im.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kennen
 * @date 2020/12/29 16:52
 */

@Slf4j
@Service
@Transactional
public class UserImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserBean getUserInfoByID(int userID) {
        UserBean bean = new UserBean();
        List<UserBean.DataBean> list = new ArrayList<>();
        UserBean.DataBean dataBean = new UserBean.DataBean();

        UserInfo info = userDao.getUserInfoByID(userID);

        dataBean.setUalipay(info.getUalipay());
        dataBean.setUid(info.getUid());
        dataBean.setUname(info.getUname());
        dataBean.setUser_img_path(info.getUser_img_path());
        list.add(dataBean);
        bean.setData(list);
        bean.setSuccess(true);
        return bean;
    }

    @Override
    public ResponseResult updateUserName(String name, int userID) {
        userDao.updateUserName(name, userID);
        log.info("用户名更新成功 -- > " + name);
        return ResponseResult.SUCCESS();
    }

    @Override
    public UpdateAvatar updateAvatar(String photo, int uid) {
        UserInfo info = userDao.getUserInfoByID(uid);

        File file = new File(Constants.AVATAR_DIR + info.getUser_img_path());
        if (file.exists()) {
            file.delete();
        }

        userDao.updateAvatar(photo, uid);
        UpdateAvatar response = new UpdateAvatar();
        response.setUser_img_path(photo);
        response.setSuccess(true);
        log.info("头像更新成功");
        return response;
    }
}
