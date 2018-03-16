/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：User.java
 * Date：18-3-15 下午4:21
 * Author：boni
 */

package com.wisfarm.gateway.entity;

import lombok.Data;

/**
 * @author keets
 * @date 2017/12/5
 */
@Data
public class User {
    private Long id;
    private String userId;
    private String password;
    private String userName;
    private String email;
    private String mobilePhone;


    public static class UserBuilder {

        private User user = new User();
        public UserBuilder withUserId(String userId){
            user.setUserId(userId);
            return this;
        }

        public UserBuilder withUserName(String userName) {
            user.setUserName(userName);
            return this;
        }

        public UserBuilder withPassword(String password) {
            user.setPassword(password);
            return this;
        }

        public User build() {
            return user;
        }
    }
}
