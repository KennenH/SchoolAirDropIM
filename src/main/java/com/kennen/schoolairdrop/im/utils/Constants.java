package com.kennen.schoolairdrop.im.utils;

public class Constants {
    /**
     * 消息类型
     */
    public interface MESSAGE_TYPE {
        /**
         * 文本类型，可带emoji
         */
        int TEXT = 0;

        /**
         * 图片类型，标记为图片类型时消息将只有一张图片
         */
        int IMAGE = 1;
    }

    public interface OFFLINE {
        String KEY_OFFLINE_FINGER_PRINT = "offlineMessageFingerPrint_";
    }

    public static final String IMAGE_DIR = "D:/assets/goods/img/";
    public static final String AVATAR_DIR = "D:/assets/user/avatars/";

    /**
     * 图片后缀
     */
    public static final String IMAGE_POST_FIX = ".jpg";

    /**
     * 离线消息表数量
     * 必须是接收者的id哈希码去mod这个数
     */
    public static final int OFFLINE_TABLE_NUMS = 5;

    /**
     * 一页数据量
     */
    public static final int ONE_PAGE_NUM = 15;
}
