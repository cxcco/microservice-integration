/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：CustomUserDetailsService.java
 * Date：18-3-7 下午10:21
 * Author：boni
 */

package com.blueskykong.auth.security.service;

import com.blueskykong.auth.entity.Permission;
import com.blueskykong.auth.entity.User;
import com.blueskykong.auth.security.extendsclz.CustomGrantedAuthority;
import com.blueskykong.auth.security.extendsclz.CustomUserDetails;
import com.blueskykong.auth.service.SecurityService;
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
    private SecurityService securityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //TODO:从数据库中读取数据
        log.info(username);

        User user = securityService.getUserByUserName(username);
        if (user != null) {
            List<Permission> permissions = securityService.getPermissionListByUserId(user.getUserId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (Permission permission : permissions) {
                if (permission != null && permission.getRoleName() != null) {
                    GrantedAuthority grantedAuthority = new CustomGrantedAuthority(permission.getPermissionUrl(), permission.getMethod());
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            // UserClientIdDTO userdto = securityService.getUserDTOByUserName(username);
            CustomUserDetails.CustomUserDetailsBuilder builder = new CustomUserDetails.CustomUserDetailsBuilder();
            CustomUserDetails userDetails = builder.withUserObject(user).withRoles(grantedAuthorities).withAuthority(grantedAuthorities).build();
            return userDetails;
        } else {
            log.error("user: " + username + " do not exist!");
            throw new UsernameNotFoundException("user: " + username + " do not exist!");
        }
    }
}
