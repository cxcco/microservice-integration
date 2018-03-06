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
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author keets
 */
@Slf4j
@Component
public class SecurityAccessDecisionManager implements AccessDecisionManager {
    /**
     * @param authentication 用户权限
     * @param o              url
     * @param collection     所需要的权限
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        log.info("decide url and permission");
        if (configAttributes == null) {
            return;
        }
        String url,method;
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if (ga instanceof CustomGrantedAuthority) {
                    CustomGrantedAuthority grantedAuthority = (CustomGrantedAuthority) ga;
                    url = grantedAuthority.getPermissionUrl();
                    method = grantedAuthority.getMethod();
                    if (matchers(url, request)) {
                        if (method.equals(request.getMethod()) || "ALL".equals(method)) {
                            //将必要的用户信息放入request中，在controller层需要使用的地方通过getAttribute方法获取对应的数据即可
                            request.setAttribute("uid", ((User) authentication.getPrincipal()).getId());
                            return;
                        }
                    }
                }
            }
        Iterator<ConfigAttribute> iterator = collection.iterator();
        //判断用户所拥有的权限，是否符合对应的Url权限，如果实现了UserDetailsService，则用户权限是loadUserByUsername返回用户所对应的权限
        while (iterator.hasNext()) {
            ConfigAttribute ca = iterator.next();
            String needRole = ca.getAttribute();
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                log.info("GrantedAuthority: {}", ga);
                if (needRole.equals(ga.getAuthority())) {
                    return;
                }
            }
        }
        log.error("AccessDecisionManager: no right!");
        throw new AccessDeniedException("no right!");
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
}

