/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：MyInterceptor.java
 * Date：18-3-16 下午4:20
 * Author：boni
 */

package com.wisfarm.demo;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.getHeader("Authentication");
        return super.preHandle(request, response, handler);
    }
}
