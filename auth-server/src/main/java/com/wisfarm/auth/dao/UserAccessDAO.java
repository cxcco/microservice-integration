/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：UserAccessDAO.java
 * Date：18-3-16 下午4:05
 * Author：boni
 */

package com.wisfarm.auth.dao;


import com.wisfarm.auth.entity.UserAccess;

import java.util.List;
import java.util.UUID;


public interface UserAccessDAO {


    List<UserAccess> securitySelectByUserId(UUID userId);

    List<UserAccess> securitySelectByUserIdWithFakeDoc(UUID userId);

    int securityInsertRecord(UserAccess record);

    int securityUpdateRecord(UserAccess record);

    int deleteByUserId(UUID userId);

}
