/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：CustomFeignConfigure.java
 * Date：18-3-16 下午12:50
 * Author：boni
 */

package com.wisfarm.gateway.feign;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;

@Configuration
public class CustomFeignConfiguration {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
    @Bean
    public CustomFeignRequestInterceptor customFeignRequestInterceptor(){
        OAuth2ClientContext oAuth2ClientContext= new DefaultOAuth2ClientContext(new DefaultAccessTokenRequest());
        return new CustomFeignRequestInterceptor(oAuth2ClientContext);
    }

}
