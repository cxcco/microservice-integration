/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：RemoteException.java
 * Date：18-3-15 下午4:36
 * Author：boni
 */

package com.wisfarm.gateway.exception;

import com.wisfarm.gateway.exception.code.ErrorCode;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class RemoteException extends AbstractException {

    public RemoteException() {
        super(INTERNAL_SERVER_ERROR);
    }

    public RemoteException(ErrorCode errorCode) {
        super(INTERNAL_SERVER_ERROR, errorCode);
    }

    public RemoteException(ErrorCode errorCode, Throwable cause) {
        super(INTERNAL_SERVER_ERROR, errorCode, cause);
    }
}
