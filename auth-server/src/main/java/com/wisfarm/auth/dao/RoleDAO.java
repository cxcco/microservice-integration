/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：RoleDAO.java
 * Date：18-3-16 下午4:05
 * Author：boni
 */

package com.wisfarm.auth.dao;

import com.wisfarm.auth.entity.Role;

import java.util.List;
import java.util.UUID;

/**
 * Created by keets on 2017/11/22.
 */
public interface RoleDAO {

    void insert(Role role);

    int deleteById(UUID id);

    void update(Role role);

    Role selectById(UUID id);

    List<Role> selectAll();

}
