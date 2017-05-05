package com.hotels.user.analytics.response;

public class BaseResponse {
    private final String code;
    private final String message;
    private final String data;

    public BaseResponse(String code, String message, String data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getData() {
        return data;
    }
}
