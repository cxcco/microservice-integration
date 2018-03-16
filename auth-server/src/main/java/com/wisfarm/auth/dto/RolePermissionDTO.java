/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：RolePermissionDTO.java
 * Date：18-3-16 下午4:05
 * Author：boni
 */

package com.wisfarm.auth.dto;

import com.wisfarm.auth.entity.Permission;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * Created by keets on 2017/11/22.
 */
@Data
public class RolePermissionDTO {

    private Long relationId;

    private UUID roleId;

    private String name;

    private Timestamp updateTime;

    private String description;

    private List<Permission> permissions;
}
