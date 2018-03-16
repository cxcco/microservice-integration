/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：TestController.java
 * Date：18-3-16 下午4:16
 * Author：boni
 */

package com.wisfarm.demo.controller;

import com.alibaba.fastjson.JSON;
import com.wisfarm.demo.domain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test")
    public String restTest(){
        domain dos = new domain();
        String jsonString = JSON.toJSONString(dos);
        System.out.println(jsonString);
        domain doss = JSON.parseObject(jsonString,domain.class);
        System.out.println(doss.toString());
        return jsonString;
    }
}
