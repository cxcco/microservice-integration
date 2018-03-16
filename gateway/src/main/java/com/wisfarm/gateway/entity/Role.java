/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：Role.java
 * Date：18-3-15 下午4:21
 * Author：boni
 */

package com.wisfarm.gateway.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * @author keets
 * @date 2017/11/22
 */
@Data
public class Role {

    private UUID id;

    private String name;

    private Timestamp updateTime;

    private String description;

    public static class RoleBuilder {
        private Role role = new Role();

        public RoleBuilder withId(UUID id) {
            role.setId(id);
            return this;
        }

        public RoleBuilder withName(String name) {
            role.setName(name);
            return this;
        }

        public RoleBuilder withDescription(String description) {
            role.setDescription(description);
            return this;
        }

        public RoleBuilder withUpdateTime(Timestamp time) {
            role.setUpdateTime(time);
            return this;
        }

        public Role build() {
            return role;
        }
    }
}
