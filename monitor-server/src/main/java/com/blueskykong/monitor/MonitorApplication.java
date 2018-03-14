/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：MonitorApplication.java
 * Date：18-3-14 下午2:32
 * Author：boni
 */

package com.blueskykong.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class MonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorApplication.class, args);
    }
}