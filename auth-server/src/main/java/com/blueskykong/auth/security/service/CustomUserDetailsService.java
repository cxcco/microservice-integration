/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：CustomUserDetailsService.java
 * Date：18-3-7 下午10:21
 * Author：boni
 */

package com.blueskykong.auth.security.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * 从DB中读取用户的权限
 */
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //TODO:从数据库中读取数据
        log.info(username);
        /*String username = authentication.getName();
        Map data = (Map) authentication.getDetails();
        String clientId = (String) data.get("client");
        Assert.hasText(clientId, "clientId must have value");
        String type = (String) data.get("type");
        String password = (String) authentication.getCredentials();

        User user =userService.getByUserName(userName);
        if (user != null) {
            List<Permission> permissions = permissionService.getByUserId(user.getId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (Permission permission : permissions) {
                if (permission != null && permission.getName()!=null) {
                    GrantedAuthority grantedAuthority = new MGrantedAuthority(permission.getPermissionUrl(),permission.getMethod());
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            user.setGrantedAuthorities(grantedAuthorities);
            return user;
        } else {
            log.error("admin: " + userName + " do not exist!");
            throw new UsernameNotFoundException("admin: " + userName + " do not exist!");
        }*/
        Map map;

        String name = username.split(":")[0];
        String password = username.split(":")[1];
        String type = username.split(":")[2];
        String clientId = username.split(":")[3];
        //如果你是调用user服务，这边不用注掉
        //map = userClient.checkUsernameAndPassword(getUserServicePostObject(username, password, type));
        map = checkUsernameAndPassword(getUserServicePostObject(username, password, type));

        if (map == null || map.isEmpty()) {
            throw new BadCredentialsException("密码错误!");
        }
        String userId = (String) map.get("userId");
        if (StringUtils.isBlank(userId)) {
            String errorCode = (String) map.get("code");
            throw new BadCredentialsException(errorCode);
        }

        CustomUserDetails customUserDetails = buildCustomUserDetails(username, password, userId, clientId);
        return customUserDetails;
    }

    private CustomUserDetails buildCustomUserDetails(String username, String password, String userId, String clientId) {
        CustomUserDetails customUserDetails = new CustomUserDetails.CustomUserDetailsBuilder()
                .withUserId(userId)
                .withPassword(password)
                .withUsername(username)
                .withClientId(clientId)
                .build();
        return customUserDetails;
    } //模拟调用user服务的方法

    private Map checkUsernameAndPassword(Map map) {
        //checkUsernameAndPassword
        if (("password").equals(map.get("password"))) {
            Map ret = new HashMap();
            ret.put("userId", UUID.randomUUID().toString());
            //ret.put("userId", "d4bd9598-7941-4025-b1bd-551eccedfaee");
            return ret;
        } else {
            return null;
        }
    }

    private Map<String, String> getUserServicePostObject(String username, String password, String type) {
        Map<String, String> requestParam = new HashMap<String, String>();
        requestParam.put("userName", username);
        requestParam.put("password", password);
        if (type != null && StringUtils.isNotBlank(type)) {
            requestParam.put("type", type);
        }
        return requestParam;
    }


}
