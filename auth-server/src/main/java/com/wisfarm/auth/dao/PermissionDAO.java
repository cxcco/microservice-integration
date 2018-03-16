/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：PermissionDAO.java
 * Date：18-3-16 下午4:05
 * Author：boni
 */

package com.wisfarm.auth.dao;

import com.wisfarm.auth.entity.Permission;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author keets
 * @date 2017/11/22
 */
public interface PermissionDAO {

    int deleteById(UUID id);

    int insert(Permission record);

    Permission selectById(UUID id);

    void updateUrl(UUID id, String newPermissionUrl);

    List<Permission> selectAll();

    List<Permission> getPermissionList(Map paramMap);
}
