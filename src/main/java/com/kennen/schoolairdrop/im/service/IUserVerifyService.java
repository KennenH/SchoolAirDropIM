package com.kennen.schoolairdrop.im.service;

/**
 * @author kennen
 * @date 2020/12/13 19:37
 */

public interface IUserVerifyService {

    /**
     * 验证用户信息
     *
     * @param id    用户唯一凭证
     * @param token 用户
     * @return 用户验证是否通过
     */
    boolean verifyUser(String id, String token);
}
