/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：Oauth2ClientProperties.java
 * Date：18-3-16 下午1:18
 * Author：boni
 */

package com.wisfarm.gateway.feign;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "paascloud.oauth2.client")
@Configuration
@Data
public class Oauth2ClientProperties {
    private String id;
    private String accessTokenUri;
    private String clientId;
    private String clientSecret;
    private String clientAuthenticationScheme;
    private String clientUserName;
    private String clientPassword;
}
