/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：MUserDetailsService.java
 * Date：18-3-6 下午3:19
 * Author：boni
 */

package com.blueskykong.auth.security.service;

import com.blueskykong.auth.domain.Permission;
import com.blueskykong.auth.domain.User;
import com.blueskykong.auth.security.CustomGrantedAuthority;
import com.blueskykong.auth.service.PermissionService;
import com.blueskykong.auth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 从DB中读取用户的权限
 */
@Slf4j
@Component
public class CustomUserDetailsService implements AuthenticationUserDetailsService<AbstractAuthenticationToken> {
    @Autowired
    UserService userService;
    @Autowired
    PermissionService permissionService;

    @Override
    public UserDetails loadUserDetails(AbstractAuthenticationToken token) throws UsernameNotFoundException {
        log.info(token.toString());
        String userName = token.getPrincipal().toString().toLowerCase();
        User user = userService.getByUserName(userName);
        if (user != null) {
            List<Permission> permissions = permissionService.getByUserId(user.getId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (Permission permission : permissions) {
                if (permission != null && permission.getName() != null) {
                    GrantedAuthority grantedAuthority = new CustomGrantedAuthority(permission.getPermissionUrl(), permission.getMethod());
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            user.setGrantedAuthorities(grantedAuthorities);
            return user;
        } else {
            log.error("admin: " + userName + " do not exist!");
            throw new UsernameNotFoundException("admin: " + userName + " do not exist!");
        }
    }
}
