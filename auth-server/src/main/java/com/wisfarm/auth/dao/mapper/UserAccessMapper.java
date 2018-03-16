/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：UserAccessMapper.java
 * Date：18-3-16 下午4:05
 * Author：boni
 */

package com.wisfarm.auth.dao.mapper;

import com.wisfarm.auth.entity.UserAccess;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;


public interface UserAccessMapper {

    List<UserAccess> securitySelectByUserId(@Param("userId") UUID userId);

    List<UserAccess> securitySelectByUserIdWithFakeDoc(@Param("userId") UUID userId);

    int securityInsertRecord(UserAccess record);

    int securityUpdateRecord(UserAccess record);

    int deleteByUserId(@Param("userId") UUID userId);

}
