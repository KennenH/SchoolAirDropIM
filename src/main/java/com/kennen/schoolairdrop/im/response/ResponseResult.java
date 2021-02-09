package com.kennen.schoolairdrop.im.response;

import com.kennen.schoolairdrop.im.utils.Constants;

public class ResponseResult {
    private boolean success;
    private String message;
    private Object data = Constants.EMPTY;

    public ResponseResult(ResponseState state) {
        this.success = state.isSuccess();
        this.message = state.getMessage();
    }

    public static ResponseResult SUCCESS() {
        return new ResponseResult(ResponseState.SUCCESS);
    }

    public static ResponseResult SUCCESS(String message) {
        ResponseResult result = new ResponseResult(ResponseState.SUCCESS);
        result.setMessage(message);
        return result;
    }

    public static ResponseResult FAILED() {
        return new ResponseResult(ResponseState.FAILED);
    }

    public static ResponseResult FAILED(String message) {
        ResponseResult result = new ResponseResult(ResponseState.FAILED);
        result.setMessage(message);
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public ResponseResult setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ResponseResult setData(Object data) {
        this.data = data;
        return this;
    }
}
