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

import javax.ws.rs.core.Response;
import java.util.UUID;

/**
 * @author keets
 * @date 2017/10/15
 */
@Slf4j
@RestController("/api")
public class SecurityController {
    @Autowired
    private UserRoleDAO userRoleDAO;

    @Autowired
    private PermissionDAO permissionDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private RolePermissionDAO rolePermissionDAO;

    @PostMapping(path = "/permission")
    public Response createPermission(Permission permission) {
        permission.setId(UUID.randomUUID());
        permissionDAO.insert(permission);
        return Response.ok(permission.getId()).build();
    }

    @GetMapping(path = "/permission/{id}")
    public Response listPermission(@PathVariable("id") String id) {
        UUID pId = UUID.fromString(id);
        Permission permission = permissionDAO.selectById(pId);
        return Response.ok(permission).build();
    }

    @PostMapping(path = "/role-permission")
    public Response createRolePermission(RolePermission rolePermission) {
        rolePermissionDAO.insert(rolePermission);
        return Response.ok().build();
    }

    @GetMapping(path = "/role")
    public Response listRoles() {
        return Response.ok(roleDAO.selectAll()).build();
    }

    @GetMapping("permissions")
    public Response listPermissions() {
        return Response.ok(permissionDAO.selectAll()).build();
    }

    @PostMapping("user-role")
    public Response createUserRole(UserRole userRole) {
        userRole.setUserId(UUID.randomUUID());
        userRoleDAO.insertUtRole(userRole);
        return Response.ok().build();
    }
}
