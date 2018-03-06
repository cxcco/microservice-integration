/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：PermissionServiceImp.java
 * Date：18-3-6 下午3:51
 * Author：boni
 */

package com.blueskykong.auth.service.serviceImp;

import com.blueskykong.auth.domain.Permission;
import com.blueskykong.auth.mapper.PermissionMapper;
import com.blueskykong.auth.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImp implements PermissionService {
    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public Permission getById(Integer id) {
        Permission permission = permissionMapper.getById(id);
        return permission;
    }

    @Override
    public List<Permission> getByUserId(Integer userId) {
        List<Permission> list = permissionMapper.getByUserId(userId);
        return list;
    }
}
