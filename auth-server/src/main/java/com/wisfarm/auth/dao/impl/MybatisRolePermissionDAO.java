/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：MybatisRolePermissionDAO.java
 * Date：18-3-16 下午4:05
 * Author：boni
 */

package com.wisfarm.auth.dao.impl;

import com.wisfarm.auth.dao.RolePermissionDAO;
import com.wisfarm.auth.dao.mapper.RolePermissionMapper;
import com.wisfarm.auth.entity.RolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * @author keets
 * @date 2017/11/22
 */
@Component
public class MybatisRolePermissionDAO implements RolePermissionDAO {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public int deleteById(Long id) {
        return rolePermissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Long insert(RolePermission record) {
        rolePermissionMapper.insert(record);
        return record.getId();
    }

    @Override
    public List<RolePermission> selectByRoleId(UUID roleId) {
        return rolePermissionMapper.selectByRoleId(roleId);
    }

    @Override
    public int updateById(RolePermission record) {
        return rolePermissionMapper.updateByPrimaryKey(record);
    }

    @Override
    public void deleteByRoleIdAndPermissionId(UUID rId, UUID pId) {
        rolePermissionMapper.deleteByRoleIdAndPermissionId(rId, pId);
    }

}
