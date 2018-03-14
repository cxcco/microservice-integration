/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：UserClientIdDTO.java
 * Date：18-3-13 下午1:28
 * Author：boni
 */

package com.blueskykong.auth.dto;

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
