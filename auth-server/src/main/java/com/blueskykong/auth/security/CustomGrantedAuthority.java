/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：CustomGrantedAuthority.java
 * Date：18-3-6 下午3:49
 * Author：boni
 */

package com.blueskykong.auth.security;

import org.springframework.security.core.GrantedAuthority;


public class CustomGrantedAuthority implements GrantedAuthority {

    private String permissionUrl;
    private String method;

    public String getPermissionUrl() {
        return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public CustomGrantedAuthority(String permissionUrl, String method) {
        this.permissionUrl = permissionUrl;
        this.method = method;
    }

    @Override
    public String getAuthority() {
        return this.permissionUrl + ";"+this.method;
    }
}
