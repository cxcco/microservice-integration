/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：PermissionService.java
 * Date：18-3-6 下午3:51
 * Author：boni
 */

package com.blueskykong.auth.service;




import com.blueskykong.auth.domain.Permission;

import java.util.List;

public interface PermissionService {

    Permission getById(Integer id);

    List<Permission> getByUserId(Integer userId);
}