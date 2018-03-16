/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：UserRoleDAO.java
 * Date：18-3-16 下午4:05
 * Author：boni
 */

package com.wisfarm.auth.dao;


import com.wisfarm.auth.dto.UserRoleDTO;
import com.wisfarm.auth.entity.UserRole;

import java.util.List;
import java.util.UUID;


public interface UserRoleDAO {

    Long insertUtRole(UserRole userRole);

    List<UserRole> selectByUserId(UUID userId);

    int updateById(UserRole record);


    int deleteByUserId(UUID userId);

    List<UserRoleDTO> selectUserRoleList(UUID userId);
}
