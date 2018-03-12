package com.blueskykong.auth.controller;

import com.blueskykong.auth.dao.PermissionDAO;
import com.blueskykong.auth.dao.RoleDAO;
import com.blueskykong.auth.dao.RolePermissionDAO;
import com.blueskykong.auth.dao.UserRoleDAO;
import com.blueskykong.auth.entity.Permission;
import com.blueskykong.auth.entity.RolePermission;
import com.blueskykong.auth.entity.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author keets
 * @date 2017/10/15
 */
@Slf4j
@RestController
public class SecurityController {
    @Autowired
    private UserRoleDAO userRoleDAO;

    @Autowired
    private PermissionDAO permissionDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private RolePermissionDAO rolePermissionDAO;

    @PostMapping(path = "/auth/permission")
    public Object createPermission(Permission permission) {
        permission.setId(UUID.randomUUID());
        permissionDAO.insert(permission);
        return "OK";
    }

    @GetMapping(path = "/auth/permission/{id}")
    public Object listPermission(@PathVariable("id") String id) {
        UUID pId = UUID.fromString(id);
        Permission permission = permissionDAO.selectById(pId);
        return "OK";
    }

    @PostMapping(path = "/auth/role-permission")
    public Object createRolePermission(RolePermission rolePermission) {
        rolePermissionDAO.insert(rolePermission);
        return "OK";
    }

    @GetMapping(path = "/auth/role")
    public Object listRoles() {
        return roleDAO.selectAll();
    }

    @GetMapping(path = "/auth/permissions")
    public Object listPermissions() {
        return permissionDAO.selectAll();
    }

    @PostMapping(path = "/auth/user-role")
    public Object createUserRole(UserRole userRole) {
        userRole.setUserId(UUID.randomUUID());
        userRoleDAO.insertUtRole(userRole);
        return "OK";
    }
}
