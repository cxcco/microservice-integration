/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：GenericErrorCodes.java
 * Date：18-3-6 下午4:38
 * Author：boni
 */

package com.blueskykong.auth.exception.code;

import com.blueskykong.auth.exception.code.ErrorCode;

public class GenericErrorCodes {
    public static ErrorCode GENERIC_API_ERROR_CODE = new ErrorCode(0001, "GENERIC_API_ERROR_CODE", "generic API error message");
    public static ErrorCode GENERIC_UNAUTHORIZED_ERROR_CODE = new ErrorCode(0002, "GENERIC_UNAUTHORIZED_ERROR_CODE", "generic unauthorized error message");
    public static ErrorCode DATA_ACCESS_ERROR_CODE = new ErrorCode(0003, "DATA_ACCESS_ERROR", "database access error");
}
