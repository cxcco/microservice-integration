/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：ClientSecretMapper.java
 * Date：18-3-16 下午4:05
 * Author：boni
 */

package com.wisfarm.auth.dao.mapper;

import com.wisfarm.auth.entity.ClientSecret;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface ClientSecretMapper {

    String getScope(@Param("clientId") String clientId, @Param("clientSecret") String clientSecret);


    int insert(ClientSecret record);

    List<ClientSecret> selectByParams(Map map);

    int updateByParams(Map map);

    int updateStatus(Map map);
}
