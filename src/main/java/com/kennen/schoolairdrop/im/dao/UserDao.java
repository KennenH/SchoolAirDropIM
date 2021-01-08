package com.kennen.schoolairdrop.im.dao;

import com.kennen.schoolairdrop.im.pojo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author kennen
 * @date 2020/12/28 23:52
 */
public interface UserDao extends JpaRepository<UserInfo, Integer>, JpaSpecificationExecutor<UserInfo> {

    @Query(value = "select * from `user_info` where uid = ?", nativeQuery = true)
    UserInfo getUserInfoByID(int userID);

    @Modifying
    @Query(value = "update `user_info` set uname = ?1 where uid = ?2", nativeQuery = true)
    void updateUserName(String name, int userID);

    @Modifying
    @Query(value = "update `user_info` set user_img_path = ?1 where uid = ?2", nativeQuery = true)
    void updateAvatar(String photo, int uid);
}