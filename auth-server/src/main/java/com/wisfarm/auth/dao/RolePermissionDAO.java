/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：RolePermissionDAO.java
 * Date：18-3-16 下午4:05
 * Author：boni
 */

package com.wisfarm.auth.dao;

import com.wisfarm.auth.entity.RolePermission;

import java.util.List;
import java.util.UUID;

/**
 * @author keets
 * @date 2017/11/22
 */
public interface RolePermissionDAO {

    int deleteById(Long id);

    Long insert(RolePermission record);

    List<RolePermission> selectByRoleId(UUID roleId);

    int updateById(RolePermission record);

    void deleteByRoleIdAndPermissionId(UUID rId, UUID pId);

}
