/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：UserFallbackClient.java
 * Date：18-3-7 下午5:19
 * Author：boni
 */

package com.blueskykong.auth.feign.fallback;

import com.blueskykong.auth.exception.RemoteException;
import com.blueskykong.auth.exception.code.ErrorCodes;
import com.blueskykong.auth.feign.client.UserClient;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserFallbackClient implements UserClient {
    @Override
    public Map checkUsernameAndPassword(Map userMap) {
        throw new RemoteException(ErrorCodes.USER_SERVICE_CALL_FAILURE);
    }

    @Override
    public Map getUserInformation(Map userCheck) {
        throw new RemoteException(ErrorCodes.USER_SERVICE_CALL_FAILURE);
    }
}
