/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：UserClient.java
 * Date：18-3-15 下午4:15
 * Author：boni
 */

package com.wisfarm.gateway.feign.client;

import com.wisfarm.gateway.entity.Permission;
import com.wisfarm.gateway.entity.User;
import com.wisfarm.gateway.feign.CustomFeignConfiguration;
import com.wisfarm.gateway.feign.CustomFeignRequestInterceptor;
import com.wisfarm.gateway.feign.fallback.UserFallbackClient;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "auth", path = "/auth",fallback = UserFallbackClient.class,configuration = CustomFeignConfiguration.class)
public interface UserClient {

    @GetMapping("/users/userName/{userName}")
    public User getUserByUserName(@PathVariable("userName") String userName);
    @GetMapping("/permissions/userId/{userId}")
    public List<Permission> getPermissionListByUserId(@PathVariable("userId") String userId);

}
