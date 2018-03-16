/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：MybatisURoleDAO.java
 * Date：18-3-16 下午4:05
 * Author：boni
 */

package com.wisfarm.auth.dao.impl;

import com.wisfarm.auth.dao.UserRoleDAO;
import com.wisfarm.auth.dao.mapper.UserRoleMapper;
import com.wisfarm.auth.dto.UserRoleDTO;
import com.wisfarm.auth.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Repository
public class MybatisURoleDAO implements UserRoleDAO {

    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    public Long insertUtRole(UserRole UserRole) {
        userRoleMapper.insert(UserRole);
        return UserRole.getId();
    }

    @Override
    public List<UserRole> selectByUserId(UUID userId) {
        return userRoleMapper.selectByUId(userId);
    }

    @Override
    public int updateById(UserRole record) {
        return userRoleMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByUserId(UUID userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        return userRoleMapper.deleteByUserId(userId);
    }

    @Override
    public List<UserRoleDTO> selectUserRoleList(UUID userId) {
        return userRoleMapper.selectUserRoleList(userId);
    }
}

