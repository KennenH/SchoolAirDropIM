package com.kennen.schoolairdrop.im.bean;

/**
 * @author kennen
 * @date 2020/12/29 17:17
 */
public class UpdateAvatar {
    /**
     * success : true
     */

    private boolean success = false;
    /**
     * user_img_path : http://106.54.110.46/Avatars/100001.jpg
     */

    private String user_img_path;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getUser_img_path() {
        return user_img_path;
    }

    public void setUser_img_path(String user_img_path) {
        this.user_img_path = user_img_path;
    }
}
