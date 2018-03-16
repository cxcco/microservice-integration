/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：ErrorCode.java
 * Date：18-3-16 下午4:05
 * Author：boni
 */

package com.wisfarm.auth.exception.code;

public class ErrorCode {

    private final int code;
    private final String message;
    private final String detailMessage;

    public ErrorCode(int code, String message, String detailMessage) {
        this.code = code;
        this.message = message;
        this.detailMessage = detailMessage;
    }

    public ErrorCode(String message, String detailMessage) {
        this.code = 0;
        this.message = message;
        this.detailMessage = detailMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDetailMessage() {
        return detailMessage;
    }
}
