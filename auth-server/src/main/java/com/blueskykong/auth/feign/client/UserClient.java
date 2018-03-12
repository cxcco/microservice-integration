/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：UserClient.java
 * Date：18-3-7 下午5:18
 * Author：boni
 */

package com.blueskykong.auth.feign.client;

import com.blueskykong.auth.feign.fallback.UserFallbackClient;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(name = "user", fallback = UserFallbackClient.class)
public interface UserClient {

    @RequestMapping(method = RequestMethod.POST, value = "/api/users/exists", consumes = {"application/json"}, produces = {"application/json"})
    Map checkUsernameAndPassword(Map userMap);

    @RequestMapping(method = RequestMethod.POST, value = "/api/users/phoneExists", consumes = {"application/json"}, produces = {"application/json"})
    Map getUserInformation(Map userCheck);

}
