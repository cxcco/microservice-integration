/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：DemoApplication.java
 * Date：18-3-14 下午4:28
 * Author：boni
 */

package com.blueskykong.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}