package com.kennen.schoolairdrop.im.dao;

import com.kennen.schoolairdrop.im.pojo.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AccessTokenDao extends JpaRepository<AccessToken, String>, JpaSpecificationExecutor<AccessToken> {

    AccessToken findOneByAccessToken(String token);
}
