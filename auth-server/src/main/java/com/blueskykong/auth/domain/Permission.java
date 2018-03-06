/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：Permission.java
 * Date：18-3-6 下午3:47
 * Author：boni
 */

package com.blueskykong.auth.domain;

import lombok.Data;

@Data
public class Permission {
    private int id;
    private String name;
    private String description;
    private String permissionUrl;
    private String method;
}
