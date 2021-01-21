package com.kennen.schoolairdrop.im.pojo;

import java.io.Serializable;

/**
 * @author kennen
 * @date 2021/1/20 13:50
 */
public class OfflineNumPK implements Serializable {

    private String client_a_id;

    private String client_b_id;

    public String getClient_a_id() {
        return client_a_id;
    }

    public void setClient_a_id(String client_a_id) {
        this.client_a_id = client_a_id;
    }

    public String getClient_b_id() {
        return client_b_id;
    }

    public void setClient_b_id(String client_b_id) {
        this.client_b_id = client_b_id;
    }
}
