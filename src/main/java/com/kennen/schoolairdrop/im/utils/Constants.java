package com.kennen.schoolairdrop.im.utils;

public class Constants {

    /**
     * 本地测试使用
     */
    public static final String SCHOOL_AIRDROP_BASE_URL = "http://81.69.14.64:8080/";

    public static final String LOCAL_BASE_URL = "http://127.0.0.1:8080/";

    /**
     * 推送通知静态公钥
     */
    public static final String PUSH_NOTIFICATION_PUBLI_KEY = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvfwQKxLJnPKzQUqu80sw\n" +
            "2jw1AxcgqDVqQ7+2uDCM7ESCXt5RcrDa0scLJ120W/6yHdibpwVEa66Pow5jyqnV\n" +
            "Dr1d3xNnQUp6NPLEsHt5kR7SRyepyiRE5nKSIWEBnHvXir92fsGOU1bGvLNqNXAy\n" +
            "QtjAnnXNTegFDt3vaLiDevPP5Ax8Cnb9H8eOcYezIG+9fhHuNZQGAgp6PnEg4Uo4\n" +
            "fRbC2/vCKD8e7V1VzVeNhMh/yVOTdUUC3I4meLnIAyW+0K7COGHaJPcNEEWLsPiv\n" +
            "lUibArk5Wmk3nR8puzfTCj7F0p3zUZFLdHxOOSmWp05qyYoiIdK0f5MDZE/rdpyG\n" +
            "jwIDAQAB\n" +
            "-----END PUBLIC KEY-----";

    /**
     * 离线消息表数量
     * 必须是接收者的id哈希码去mod这个数
     */
    public static final int OFFLINE_TABLE_NUMS = 5;

    /**
     * 一页数据量
     */
    public static final int ONE_PAGE_NUM = 10;
}
