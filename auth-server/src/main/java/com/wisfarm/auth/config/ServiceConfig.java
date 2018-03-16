/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：ServiceConfig.java
 * Date：18-3-16 下午4:05
 * Author：boni
 */

package com.wisfarm.auth.config;

import com.wisfarm.auth.exception.CustomWebResponseExceptionTranslator;
import com.wisfarm.auth.exception.CustomWebResponseExceptionTranslator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

@Configuration
public class ServiceConfig {

    @Bean
    public WebResponseExceptionTranslator webResponseExceptionTranslator() {
        return new CustomWebResponseExceptionTranslator();
    }

}
