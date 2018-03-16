/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：AuthApplication.java
 * Date：18-3-16 下午4:05
 * Author：boni
 */

package com.wisfarm.auth;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

/**
 * @author keets
 * @date 2016/12/5
 */

@SpringBootApplication
@EnableDiscoveryClient
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

}