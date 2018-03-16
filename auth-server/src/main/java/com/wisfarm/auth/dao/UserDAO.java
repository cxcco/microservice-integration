/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：UserDAO.java
 * Date：18-3-16 下午4:05
 * Author：boni
 */

package com.wisfarm.auth.dao;


import com.wisfarm.auth.dto.UserClientIdDTO;
import com.wisfarm.auth.entity.User;
import com.wisfarm.auth.dto.UserClientIdDTO;
import com.wisfarm.auth.entity.User;


public interface UserDAO {

    Long insertUser(User user);

    User selectByUserName(String userName);

    int updateUserByUserName(User user);

    int deleteByUserName(String userName);

    UserClientIdDTO getUserClientDTOByUserName(String userName);

}
