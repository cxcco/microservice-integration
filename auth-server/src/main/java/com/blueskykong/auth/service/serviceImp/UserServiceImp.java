/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：UserServiceImp.java
 * Date：18-3-6 下午3:51
 * Author：boni
 */

package com.blueskykong.auth.service.serviceImp;


import com.blueskykong.auth.domain.User;
import com.blueskykong.auth.mapper.UserMapper;
import com.blueskykong.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User getById(Integer id) {
       User user = userMapper.getById(id);
       return user;
    }

    @Override
    public User getByUserName(String userName) {
       User user = userMapper.getByUserName(userName);
       return user;
    }
}
