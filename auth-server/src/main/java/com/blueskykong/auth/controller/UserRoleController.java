package com.blueskykong.auth.controller;

import com.blueskykong.auth.dto.RolePermissionDTO;
import com.blueskykong.auth.entity.Permission;
import com.blueskykong.auth.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * @author keets
 * @date 2017/11/22
 */
@RestController
public class UserRoleController {

    @Autowired
    SecurityService securityService;

    @GetMapping(path = "/auth/userRolePermissions")
    public Object getUserRolePermissions(@RequestParam("userId") String userId) {
        List<RolePermissionDTO> rolePermissions = securityService.getRolePermissionsByUserId(UUID.fromString(userId));
        return rolePermissions;
    }

    @GetMapping("/auth/userPermissions")
    public Object getPermissionList(@RequestParam("userId") String userId) {
        List<Permission> permissionList = securityService.getPermissionListByUserId(userId);
        return permissionList;
    }
}
