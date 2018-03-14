/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：CustomAuthenticationToken.java
 * Date：18-3-13 下午1:21
 * Author：boni
 */

package com.blueskykong.auth.security.extendsclz;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by keets on 2017/8/5.
 */
public class CustomAuthenticationToken extends AbstractAuthenticationToken {

    //private CustomUserDetails userDetails;
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