package com.blueskykong.auth.controller;

import com.blueskykong.auth.dto.ApiClientDTO;
import com.blueskykong.auth.service.ClientSecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author keets
 * @date 2017/9/18
 */
@RestController("/api")
public class ClientSecretController {

    @Autowired
    private ClientSecretService clientSecretService;

    @PostMapping(path = "/clients")
    public Response createClient(ApiClientDTO apiClientDTO) {
        clientSecretService.createClientSecret(apiClientDTO);
        return Response.status(Response.Status.CREATED).build();
    }

    @GetMapping("/clients/{clientId}")
    public Response getClient(@PathVariable("clientId") String clientId) {
        ApiClientDTO apiClientDTO = clientSecretService.getClientSecretByClientId(clientId);
        return Response.ok(apiClientDTO).build();
    }


    @PutMapping("/clients/{clientId}")
    public Response updateClient(@PathVariable("clientId") String clientId, ApiClientDTO apiClientDTO) {
        clientSecretService.updateClientSecret(apiClientDTO);
        return Response.ok().build();
    }
}
