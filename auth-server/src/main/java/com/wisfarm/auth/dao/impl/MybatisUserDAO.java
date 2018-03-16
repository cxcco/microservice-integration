/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：MybatisUserDAO.java
 * Date：18-3-16 下午4:05
 * Author：boni
 */

package com.wisfarm.auth.dao.impl;

import com.wisfarm.auth.dao.RoleDAO;
import com.wisfarm.auth.dao.UserDAO;
import com.wisfarm.auth.dao.mapper.RoleMapper;
import com.wisfarm.auth.dao.mapper.UserMapper;
import com.wisfarm.auth.dto.UserClientIdDTO;
import com.wisfarm.auth.entity.Role;
import com.wisfarm.auth.entity.User;
import com.wisfarm.auth.dao.UserDAO;
import com.wisfarm.auth.dao.mapper.UserMapper;
import com.wisfarm.auth.dto.UserClientIdDTO;
import com.wisfarm.auth.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class MybatisUserDAO implements UserDAO {

    @Autowired
private UserMapper userMapper;

    @Override
    public Long insertUser(User user) {
        userMapper.insertUser(user);
        return user.getId();
    }

    @Override
    public User selectByUserName(String userName) {
        return userMapper.selectByUserName(userName);
    }

    @Override
    public int updateUserByUserName(User user) {
        return userMapper.updateUserByUserName(user);
    }

    @Override
    public int deleteByUserName(String userName) {
        return userMapper.deleteByUserName(userName);
    }

    @Override
    public UserClientIdDTO getUserClientDTOByUserName(String userName) {
        return userMapper.getUserClientDTOByUserName(userName);
    }
}

