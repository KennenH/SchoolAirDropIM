package com.kennen.schoolairdrop.im.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_access_tokens")
public class AccessToken {

    @Column(name = "access_token")
    private String accessToken;

    @Id
    @Column(name = "user_id")
    private int userID;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
