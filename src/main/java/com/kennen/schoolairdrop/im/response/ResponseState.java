package com.kennen.schoolairdrop.im.response;

public enum ResponseState {
    SUCCESS(200, "请求成功"),
    FAILED(400, "请求失败");

    ResponseState(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

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
