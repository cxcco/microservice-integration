/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：ClientSecretController.java
 * Date：18-3-16 下午4:05
 * Author：boni
 */

package com.wisfarm.auth.controller;

import com.wisfarm.auth.dto.ApiClientDTO;
import com.wisfarm.auth.service.ClientSecretService;
import com.wisfarm.auth.service.ClientSecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientSecretController {

    @Autowired
    private ClientSecretService clientSecretService;

    @PostMapping(path = "/auth/lients")
    public Object createClient(ApiClientDTO apiClientDTO) {
        clientSecretService.createClientSecret(apiClientDTO);
        return "OK";
    }

    @GetMapping("/auth/clients/{clientId}")
    public Object getClient(@PathVariable("clientId") String clientId) {
        ApiClientDTO apiClientDTO = clientSecretService.getClientSecretByClientId(clientId);
        return apiClientDTO;
    }

    @PutMapping("/auth/clients/{clientId}")
    public Object updateClient(@PathVariable("clientId") String clientId, ApiClientDTO apiClientDTO) {
        clientSecretService.updateClientSecret(apiClientDTO);
        return "OK";
    }
}
