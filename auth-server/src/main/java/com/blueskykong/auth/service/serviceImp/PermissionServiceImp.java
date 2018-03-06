package com.example.cas.service.serviceImp;

import com.example.cas.domain.Permission;
import com.example.cas.mapper.PermissionMapper;
import com.example.cas.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
