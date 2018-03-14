/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：SecureResourceFilterInvocationDefinitionSource.java
 * Date：18-3-6 下午3:17
 * Author：boni
 */

package com.blueskykong.auth.security.filter.authorization;

import com.blueskykong.auth.security.extendsclz.CustomConfigAttribute;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j
@Component
public class CustomFilterInvocationMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        log.info("getAttributes");
        //应该做instanceof
        final HttpServletRequest request = ((FilterInvocation) object).getRequest();
        Set<ConfigAttribute> allAttributes = new HashSet<>();
        ConfigAttribute configAttribute = new CustomConfigAttribute(request);
        allAttributes.add(configAttribute);
        return allAttributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

}

