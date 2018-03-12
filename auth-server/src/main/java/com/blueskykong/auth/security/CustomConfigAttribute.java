/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：CustomConfigAttribute.java
 * Date：18-3-7 下午8:39
 * Author：boni
 */

package com.blueskykong.auth.security;

import org.springframework.security.access.ConfigAttribute;

import javax.servlet.http.HttpServletRequest;

public class CustomConfigAttribute implements ConfigAttribute {

    private final HttpServletRequest httpServletRequest;

    public CustomConfigAttribute(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }


    @Override
    public String getAttribute() {
        return null;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }
}