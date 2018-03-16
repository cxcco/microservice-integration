/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：UserMapper.java
 * Date：18-3-13 上午10:46
 * Author：boni
 */

package com.blueskykong.auth.dao.mapper;

import com.blueskykong.auth.dto.UserClientIdDTO;
import com.blueskykong.auth.dto.UserRoleDTO;
import com.blueskykong.auth.entity.User;
import com.blueskykong.auth.entity.UserRole;
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
