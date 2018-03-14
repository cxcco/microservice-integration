/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：TestController.java
 * Date：18-3-14 下午4:35
 * Author：boni
 */

package com.blueskykong.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test")
    public String restTest(){
        return "this is a msg from demo-server!";
    }
}
