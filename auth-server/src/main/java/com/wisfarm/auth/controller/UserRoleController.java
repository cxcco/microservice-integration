/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：UserRoleController.java
 * Date：18-3-16 下午4:05
 * Author：boni
 */

package com.wisfarm.auth.controller;

import com.wisfarm.auth.dto.RolePermissionDTO;
import com.wisfarm.auth.entity.Permission;
import com.wisfarm.auth.entity.User;
import com.wisfarm.auth.service.SecurityService;
import com.wisfarm.auth.entity.Permission;
import com.wisfarm.auth.entity.User;
import com.wisfarm.auth.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class UserRoleController {

    @Autowired
    SecurityService securityService;

    @GetMapping(path = "/auth/users/userName/{userName}")
    public User getUserByUserName(@PathVariable("userName") String userName) {
        User user = securityService.getUserByUserName(userName);
        return user;
    }

    @GetMapping("/auth/permissions/userId/{userId}")
    public List<Permission> getPermissionList(@PathVariable("userId") String userId) {
        List<Permission> permissionList = securityService.getPermissionListByUserId(userId);
        return permissionList;
    }
}
