package com.kennen.schoolairdrop.im.response;

public enum ResponseState {
    SUCCESS(true, 20000, "请求成功"),
    FAILED(false, 40000, "请求失败");

    ResponseState(boolean success, int code, String message) {
        isSuccess = success;
        this.code = code;
        this.message = message;
    }

    private boolean isSuccess;
    private int code;
    private String message;


    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
