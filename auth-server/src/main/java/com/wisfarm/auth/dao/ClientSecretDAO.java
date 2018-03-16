/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：ClientSecretDAO.java
 * Date：18-3-16 下午4:05
 * Author：boni
 */

package com.wisfarm.auth.dao;

import com.wisfarm.auth.entity.ClientSecret;
import com.wisfarm.auth.entity.ClientSecretStatus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.UUID;


@Mapper
public interface ClientSecretDAO {
    int create(ClientSecret clientSecret);

    String getScope(String clientId, String clientSecret);

    List<ClientSecret> get(ClientSecret clientSecret);

    int updateStatusByTenantId(UUID tenantId, ClientSecretStatus status);

    int updateStatusByClientId(String clientId, ClientSecretStatus status);

    int update(ClientSecret clientSecret);
}
