/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：OAuth2FeignRequestInterceptor.java
 * Date：18-3-15 下午6:00
 * Author：boni
 */
package com.wisfarm.gateway.feign;

import com.wisfarm.gateway.constants.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.http.AccessTokenRequiredException;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.util.Arrays;

/**
 * Created by beaver on 2017/6/3.
 */
@Slf4j
public class CustomFeignRequestInterceptor extends OAuth2FeignRequestInterceptor {

    @Autowired
    Oauth2ClientProperties oauth2ClientProperties;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    private final OAuth2ClientContext oAuth2ClientContext ;

    public CustomFeignRequestInterceptor(OAuth2ClientContext oAuth2ClientContext){
        super(oAuth2ClientContext,null);
        this.oAuth2ClientContext = oAuth2ClientContext;
    }

    @Override
    protected OAuth2AccessToken acquireAccessToken() throws UserRedirectRequiredException {
        return acquireAccessToken(resource());
    }

    protected  OAuth2AccessToken acquireAccessToken(OAuth2ProtectedResourceDetails resource){
        AccessTokenRequest tokenRequest = oAuth2ClientContext.getAccessTokenRequest();
        if (tokenRequest == null) {
            throw new AccessTokenRequiredException(
                    "Cannot find valid context on request for resource '"
                            + resource.getId() + "'.",
                    resource);
        }
        String stateKey = tokenRequest.getStateKey();
        if (stateKey != null) {
            tokenRequest.setPreservedState(
                    oAuth2ClientContext.removePreservedState(stateKey));
        }
        OAuth2AccessToken existingToken = oAuth2ClientContext.getAccessToken();
        if (existingToken != null) {
            oAuth2ClientContext.setAccessToken(existingToken);
        }
        OAuth2AccessToken obtainableAccessToken;
        obtainableAccessToken = new ResourceOwnerPasswordAccessTokenProvider().obtainAccessToken(resource,
                tokenRequest);
        if (obtainableAccessToken == null || obtainableAccessToken.getValue() == null) {
            throw new IllegalStateException(
                    " Access token provider returned a null token, which is illegal according to the contract.");
        }
        oAuth2ClientContext.setAccessToken(obtainableAccessToken);
        return obtainableAccessToken;
    }

    protected OAuth2ProtectedResourceDetails resource() {

        ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
        ServiceInstance serviceInstance = loadBalancerClient.choose(SecurityConstants.AUTH_SERVICE);
        if (serviceInstance == null) {
            throw new RuntimeException("Failed to choose an auth instance.");
        }
        String accessTokenUrl = serviceInstance.getUri().toString()+ oauth2ClientProperties.getAccessTokenUri();
        resource.setAccessTokenUri(accessTokenUrl);

        resource.setClientAuthenticationScheme(AuthenticationScheme.header);
        resource.setClientId(oauth2ClientProperties.getClientId());
        resource.setClientSecret(oauth2ClientProperties.getClientSecret());

        resource.setGrantType("password");
        resource.setScope(Arrays.asList("all"));

        resource.setUsername(oauth2ClientProperties.getClientUserName());
        resource.setPassword(oauth2ClientProperties.getClientPassword());

        return resource;
    }

}
