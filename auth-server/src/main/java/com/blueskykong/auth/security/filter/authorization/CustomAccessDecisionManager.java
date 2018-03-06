/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：SecurityAccessDecisionManager.java
 * Date：18-3-6 下午3:17
 * Author：boni
 */

package com.blueskykong.auth.security.filter.authorization;

import com.blueskykong.auth.security.CustomGrantedAuthority;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.Iterator;

/**
 * @author keets
 */
@Slf4j
@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {
    /**
     * @param authentication 用户权限
     * @param object         用户请求
     * @param configAttributes    所需要的权限
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        log.info("decide url and permission");
        if (configAttributes == null) {
            return;
        }
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        String url,method;
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if (ga instanceof CustomGrantedAuthority) {
                    CustomGrantedAuthority grantedAuthority = (CustomGrantedAuthority) ga;
                    url = grantedAuthority.getPermissionUrl();
                    method = grantedAuthority.getMethod();
                    if (matchers(url, request)) {
                        if (method.equals(request.getMethod()) || "ALL".equals(method)) {
                            return;
                        }
                    }
                }
            }
        log.error("AccessDecisionManager: no right!");
        throw new AccessDeniedException("AccessDecisionManager: no right!");
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

    private boolean matchers(String url, HttpServletRequest request) {
        AntPathRequestMatcher matcher = new AntPathRequestMatcher(url);
        return matcher.matches(request);
    }
}

