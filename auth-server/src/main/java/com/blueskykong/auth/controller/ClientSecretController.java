package com.blueskykong.auth.controller;

import com.blueskykong.auth.dto.ApiClientDTO;
import com.blueskykong.auth.service.ClientSecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author keets
 * @date 2017/9/18
 */
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
