/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：UserRoleDTO.java
 * Date：18-3-16 下午4:05
 * Author：boni
 */

package com.wisfarm.auth.dto;

import lombok.Data;

import java.util.UUID;

/**
 * Created by keets on 2016/12/5.
 */
@Data
public class UserRoleDTO {

    private UUID userId;

    private UUID roleId;

    private String name;

    private String description;
}
