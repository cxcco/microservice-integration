/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：CustomAuthenticationToken.java
 * Date：18-3-15 下午1:13
 * Author：boni
 */

package com.wisfarm.auth.security.authorization.extendsclz;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by keets on 2017/8/5.
 */
public class CustomAuthenticationToken extends AbstractAuthenticationToken {

    private UserDetails userDetails;

    public CustomAuthenticationToken(UserDetails userDetails) {
        super(null);
        this.userDetails = userDetails;
        super.setAuthenticated(true);
    }

    public Object getPrincipal() {
        return this.userDetails;
    }

    public Object getCredentials() {
        return this.userDetails.getPassword();
    }

}