/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：ApiClientDTO.java
 * Date：18-3-16 下午4:05
 * Author：boni
 */

package com.wisfarm.auth.dto;

import com.wisfarm.auth.exception.code.ErrorCodes;
import com.wisfarm.auth.exception.code.ErrorCodes;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Created by keets on 2016/12/5.
 */
public class ApiClientDTO {

    @NotBlank(message = ErrorCodes.CLIENT_ID_IS_NULL_STR)
    @NotNull(message = ErrorCodes.CLIENT_ID_IS_NULL_STR)
    private String clientId;

    @NotBlank(message = ErrorCodes.CLIENT_SECRET_IS_NULL_STR)
    @NotNull(message = ErrorCodes.CLIENT_SECRET_IS_NULL_STR)
    private String clientSecret;

    private String status;

    private String purpose;

    @NotNull(message = ErrorCodes.ORGANIZAITON_ID_IS_NULL_STR)
    private UUID tenantId;

    private UUID userId;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public UUID getTenantId() {
        return tenantId;
    }

    public void setTenantId(UUID tenantId) {
        this.tenantId = tenantId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
