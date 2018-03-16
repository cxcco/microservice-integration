/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：MybatisPermissionDAO.java
 * Date：18-3-16 下午4:05
 * Author：boni
 */

package com.wisfarm.auth.dao.impl;

import com.wisfarm.auth.dao.PermissionDAO;
import com.wisfarm.auth.dao.mapper.PermissionMapper;
import com.wisfarm.auth.entity.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author keets
 * @date 2017/11/22
 */
@Repository
public class MybatisPermissionDAO implements PermissionDAO {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public int deleteById(UUID id) {
        return permissionMapper.deleteById(id);
    }

    @Override
    public int insert(Permission record) {
        return permissionMapper.insert(record);
    }

    @Override
    public Permission selectById(UUID id) {
        return permissionMapper.selectById(id);
    }

    @Override
    public void updateUrl(UUID id, String newPermissionUrl) {
        permissionMapper.updateUrl(id, newPermissionUrl);
    }

    @Override
    public List<Permission> selectAll() {
        return permissionMapper.selectAll();
    }

    @Override
    public List<Permission> getPermissionList(Map paramMap) {
        return permissionMapper.selectByMap(paramMap);
    }
}
