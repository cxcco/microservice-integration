/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：RedisProperties.java
 * Date：18-3-16 下午4:05
 * Author：boni
 */

package com.wisfarm.auth.properties;

/**
 *
 * @author keets
 * @date 2016/12/5
 */
public class RedisProperties {

    private String host;

    private Integer port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
