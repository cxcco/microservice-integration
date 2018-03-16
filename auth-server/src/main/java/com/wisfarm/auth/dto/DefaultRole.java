/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：DefaultRole.java
 * Date：18-3-16 下午4:05
 * Author：boni
 */

package com.wisfarm.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.UUID;

/**
 * Created by keets on 2017/11/22.
 */

@JsonPropertyOrder({
        "id",
        "name",
        "description"})
@Data
public class DefaultRole {
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public DefaultRole(UUID id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

}
