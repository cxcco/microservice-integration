/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：CustomAccessDecisionManager.java
 * Date：18-3-15 下午4:15
 * Author：boni
 */

package com.wisfarm.gateway.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Slf4j
@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    /**
     * @param authentication   用户权限
     * @param object           用户请求
     * @param configAttributes 所需要的权限
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        log.info("decide url and permission");
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        String url, method;
        if ("anonymousUser".equals(authentication.getPrincipal())
                || matches("/images/**", request)
                || matches("/js/**", request)
                || matches("/css/**", request)
                || matches("/fonts/**", request)
                || matches("/index.html", request)
                || matches("/favicon.ico", request)
                ) {
            return;
        } else {
            //使用这种方法来获取用户的权限来自于AuthenticationProvider，
            //这个数据是被一次性存到了oauth_access_token表中，所以会存在权限更新不及时的问题，所以必须重新加载一次userDetail数据
            //CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            CustomUserDetails userDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(authentication.getName());
            for (GrantedAuthority ga : userDetails.getAuthorities()) {
                if (ga instanceof CustomGrantedAuthority) {
                    CustomGrantedAuthority grantedAuthority = (CustomGrantedAuthority) ga;
                    url = grantedAuthority.getPermissionUrl();
                    method = grantedAuthority.getMethod();
                    if (matches(url, request)) {
                        if (method.equals(request.getMethod()) || "ALL".equals(method)) {
                            return;
                        }
                    }
                }
            }
        }
        log.error("no right!");
        throw new AccessDeniedException("U have no right to access resources!");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    //是否支持FilterInvocation需要将这里的false改为true
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    private boolean matches(String url, HttpServletRequest request) {
        AntPathRequestMatcher matcher = new AntPathRequestMatcher(url);
        return matcher.matches(request);
    }
}

