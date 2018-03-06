/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：PermissionMapper.java
 * Date：18-3-6 下午3:52
 * Author：boni
 */

package com.blueskykong.auth.mapper;

import com.blueskykong.auth.domain.Permission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper {

    @Select("SELECT * FROM sys_permission WHERE id =#{id}")
    @ResultType(Permission.class)
    Permission getById(@Param("id") Integer id);

    @Select("select p.* from sys_user u " +
            "LEFT JOIN sys_role_user ur on u.id= ur.user_id " +
            "LEFT JOIN sys_role r on ur.role_id=r.id " +
            "LEFT JOIN sys_permission_role rp on rp.role_id=r.id " +
            "LEFT JOIN sys_permission p on p.id =rp.permission_id " +
            "where u.id=#{userId}")
    @ResultType(Permission.class)
    List<Permission> getByUserId(@Param("userId") Integer userId);
}
