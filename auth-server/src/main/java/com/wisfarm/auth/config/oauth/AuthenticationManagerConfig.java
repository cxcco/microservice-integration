/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：AuthenticationManagerConfig.java
 * Date：18-3-16 下午4:05
 * Author：boni
 */

package com.wisfarm.auth.config.oauth;

import com.wisfarm.auth.security.authorization.provider.CustomAuthenticationProvider;
import com.wisfarm.auth.security.authorization.service.CustomUserDetailsService;
import com.wisfarm.auth.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;

//授权类型为password时使用该认证管理器
@Configuration
public class AuthenticationManagerConfig extends GlobalAuthenticationConfigurerAdapter {
    @Autowired
    CustomAuthenticationProvider customAuthenticationProvider;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }
}
