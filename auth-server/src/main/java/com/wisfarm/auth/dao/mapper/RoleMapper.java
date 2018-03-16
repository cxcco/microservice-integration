/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：RoleMapper.java
 * Date：18-3-16 下午4:05
 * Author：boni
 */

package com.wisfarm.auth.dao.mapper;

import com.wisfarm.auth.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

/**
 * @author keets
 * @date 2017/11/22
 */

public interface RoleMapper {

    int deleteByPrimaryKey(@Param("id") UUID id);

    int insert(Role record);

    Role selectByPrimaryKey(@Param("id") UUID id);

    int updateByPrimaryKey(Role record);

    List<Role> selectAll();

}
