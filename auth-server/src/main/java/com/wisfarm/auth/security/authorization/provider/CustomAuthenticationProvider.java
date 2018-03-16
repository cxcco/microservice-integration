/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：CustomAuthenticationProvider.java
 * Date：18-3-15 下午1:10
 * Author：boni
 */

package com.wisfarm.auth.security.authorization.provider;

import com.auth0.jwt.internal.org.apache.commons.lang3.StringUtils;
import com.wisfarm.auth.security.authorization.extendsclz.CustomAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 在AuthenticationManagerConfig中被注入，通过AuthenticationUserDetailsService加载UserDtails对象
 */
@Slf4j
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(log.isDebugEnabled()){
            log.debug("through authenticationProvider");
        }
        String username = (String) authentication.getPrincipal();
        UserDetails customUserDetails = userDetailsService.loadUserByUsername(username);
        String name = customUserDetails.getUsername();
        if (StringUtils.isBlank(name)) {
            Map data = (Map) authentication.getDetails();
            String errorCode = (String) data.get("code");
            throw new BadCredentialsException(errorCode);
        }
        return new CustomAuthenticationToken(customUserDetails);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

}