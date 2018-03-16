/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：DemoApplication.java
 * Date：18-3-16 下午4:16
 * Author：boni
 */

package com.wisfarm.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}