/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：UserMapper.java
 * Date：18-3-16 下午4:05
 * Author：boni
 */

package com.wisfarm.auth.dao.mapper;

import com.wisfarm.auth.dto.UserClientIdDTO;
import com.wisfarm.auth.dto.UserRoleDTO;
import com.wisfarm.auth.entity.User;
import com.wisfarm.auth.entity.UserRole;
import com.wisfarm.auth.dto.UserClientIdDTO;
import com.wisfarm.auth.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

public interface UserMapper {

    Long insertUser(User user);

    User selectByUserName(@Param("userName") String userName);

    int updateUserByUserName(User user);

    int deleteByUserName(@Param("userName") String userName);

    UserClientIdDTO getUserClientDTOByUserName(@Param("userName")String userName);
}
