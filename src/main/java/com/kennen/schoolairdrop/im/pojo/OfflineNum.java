package com.kennen.schoolairdrop.im.pojo;

import javax.persistence.*;

@Entity
@IdClass(OfflineNumPK.class)
@Table(name = "offline_nums")
public class OfflineNum {

    @Id
    @Column(name = "client_a_id")
    private String client_a_id;

    @Id
    @Column(name = "client_b_id")
    private String client_b_id;

    @Column(name = "offline_num_to_a")
    private int offline_num_to_a;

    @Column(name = "offline_num_to_b")
    private int offline_num_to_b;

    @Column(name = "fingerprint")
    private String fingerprint;

    @Column(name = "latest_sender")
    private int latest_sender;

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


    public int getOffline_num_to_a() {
        return offline_num_to_a;
    }

    public void setOffline_num_to_a(int offline_num_to_a) {
        this.offline_num_to_a = offline_num_to_a;
    }


    public int getOffline_num_to_b() {
        return offline_num_to_b;
    }

    public void setOffline_num_to_b(int offline_num_to_b) {
        this.offline_num_to_b = offline_num_to_b;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public int getLatest_sender() {
        return latest_sender;
    }

    public void setLatest_sender(int latest_sender) {
        this.latest_sender = latest_sender;
    }
}
