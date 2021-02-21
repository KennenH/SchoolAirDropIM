package com.kennen.schoolairdrop.im.response;

public class ResponseResult {
    private int code;
    private String msg;
    private Object data = null;

    public ResponseResult(ResponseState state) {
        this.code = state.getCode();
        this.msg = state.getMessage();
    }

    public static ResponseResult SUCCESS() {
        return new ResponseResult(ResponseState.SUCCESS);
    }

    public static ResponseResult SUCCESS(String message) {
        ResponseResult result = new ResponseResult(ResponseState.SUCCESS);
        result.setMsg(message);
        return result;
    }

    public static ResponseResult FAILED() {
        return new ResponseResult(ResponseState.FAILED);
    }

    public static ResponseResult FAILED(String message) {
        ResponseResult result = new ResponseResult(ResponseState.FAILED);
        result.setMsg(message);
        return result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public ResponseResult setData(Object data) {
        this.data = data;
        return this;
    }
}
