/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：RolePermission.java
 * Date：18-3-16 下午4:05
 * Author：boni
 */

package com.wisfarm.auth.entity;

import lombok.Data;

import java.util.UUID;

/**
 * Created by keets on 2017/11/22.
 */
@Data
public class RolePermission {

    private Long id;

    private UUID roleId;

    private UUID permissionId;
}
