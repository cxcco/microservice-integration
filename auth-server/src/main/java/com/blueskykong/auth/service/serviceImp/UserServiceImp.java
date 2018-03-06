package com.example.cas.service.serviceImp;

import com.example.cas.domain.User;
import com.example.cas.mapper.UserMapper;
import com.example.cas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
