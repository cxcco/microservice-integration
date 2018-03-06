/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：UserService.java
 * Date：18-3-6 下午3:51
 * Author：boni
 */

package com.blueskykong.auth.service;


import com.blueskykong.auth.domain.User;

public interface UserService {

	User getById(Integer id);
	User getByUserName(String userName);
}