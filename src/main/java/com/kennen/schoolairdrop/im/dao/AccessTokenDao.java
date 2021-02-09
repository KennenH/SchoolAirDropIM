package com.kennen.schoolairdrop.im.dao;

import com.kennen.schoolairdrop.im.pojo.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface AccessTokenDao extends JpaRepository<AccessToken, Integer>, JpaSpecificationExecutor<AccessToken> {

    @Query(value = "select * from user_access_tokens where access_token = ?1",nativeQuery = true)
    AccessToken findOneByAccessToken(String token);
}
