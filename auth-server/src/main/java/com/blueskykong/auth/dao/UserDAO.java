/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：UserDAO.java
 * Date：18-3-13 上午10:28
 * Author：boni
 */

package com.blueskykong.auth.dao;


import com.blueskykong.auth.dto.UserClientIdDTO;
import com.blueskykong.auth.entity.User;


public interface UserDAO {

    Long insertUser(User user);

    User selectByUserName(String userName);

    int updateUserByUserName(User user);

    int deleteByUserName(String userName);

    UserClientIdDTO getUserClientDTOByUserName(String userName);

}
