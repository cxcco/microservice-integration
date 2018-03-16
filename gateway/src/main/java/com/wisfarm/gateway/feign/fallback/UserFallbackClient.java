/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：UserFallbackClient.java
 * Date：18-3-7 下午5:19
 * Author：boni
 */

package com.wisfarm.gateway.feign.fallback;

import com.wisfarm.gateway.entity.Permission;
import com.wisfarm.gateway.entity.User;
import com.wisfarm.gateway.exception.RemoteException;
import com.wisfarm.gateway.exception.code.ErrorCodes;
import com.wisfarm.gateway.feign.client.UserClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFallbackClient implements UserClient {
    @Override
    public User getUserByUserName(String userName) {
        throw new RemoteException(ErrorCodes.USER_SERVICE_CALL_FAILURE);
    }

    @Override
    public List<Permission> getPermissionListByUserId(String userId) {
        throw new RemoteException(ErrorCodes.USER_SERVICE_CALL_FAILURE);
    }

}
