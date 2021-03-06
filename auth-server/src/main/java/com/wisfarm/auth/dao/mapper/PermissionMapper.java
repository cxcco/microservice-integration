/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：PermissionMapper.java
 * Date：18-3-16 下午4:05
 * Author：boni
 */

package com.wisfarm.auth.dao.mapper;

import com.wisfarm.auth.entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by keets on 2017/11/22.
 */
public interface PermissionMapper {

    int deleteById(@Param("id") UUID id);

    int insert(Permission record);

    Permission selectById(@Param("id") UUID id);

    List<Permission> selectAll();

    int updateById(Permission record);

    void updateUrl(UUID id, String newPermissionUrl);

    List<Permission> selectByMap(Map paraMap);
}
