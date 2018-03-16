/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：UserClientIdDTO.java
 * Date：18-3-16 下午4:05
 * Author：boni
 */

package com.wisfarm.auth.dto;

import lombok.Data;

@Data
public class UserClientIdDTO {
    private Long id;
    private String userId;
    private String password;
    private String userName;
    private String email;
    private String mobilePhone;
    private String clientId;
}
