/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：CustomUserDetailsService.java
 * Date：18-3-15 下午4:41
 * Author：boni
 */

package com.wisfarm.gateway.security;

import com.wisfarm.gateway.entity.Permission;
import com.wisfarm.gateway.entity.User;
import com.wisfarm.gateway.feign.client.UserClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * 从DB中读取用户的权限
 */
@Slf4j
@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserClient userCliet;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("load userData from DB by username={}",username);

        User user = userCliet.getUserByUserName(username);
        if (user != null) {
            List<Permission> permissions = userCliet.getPermissionListByUserId(user.getUserId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (Permission permission : permissions) {
                if (permission != null && permission.getRoleName() != null) {
                    GrantedAuthority grantedAuthority = new CustomGrantedAuthority(permission.getPermissionUrl(), permission.getMethod());
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            CustomUserDetails.CustomUserDetailsBuilder builder = new CustomUserDetails.CustomUserDetailsBuilder();
            CustomUserDetails userDetails = builder.withUserObject(user).withRoles(grantedAuthorities).withAuthority(grantedAuthorities).build();
            return userDetails;
        } else {
            log.error("user: " + username + " do not exist!");
            throw new UsernameNotFoundException("user do not exist!");
        }
    }
}
