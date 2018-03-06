/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：UserMapper.java
 * Date：18-3-6 下午3:52
 * Author：boni
 */

package com.blueskykong.auth.mapper;

import com.blueskykong.auth.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    @Select("select * from sys_user where id=#{id}")
    @ResultType(User.class)
    User getById(@Param("id") Integer id);

    @Select("select * from sys_user where username=#{userName}")
    @ResultType(User.class)
    User getByUserName(@Param("userName") String userName);
}
