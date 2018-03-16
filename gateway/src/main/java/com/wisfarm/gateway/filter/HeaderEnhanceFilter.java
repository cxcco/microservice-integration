/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：MyZuulFilter.java
 * Date：18-3-12 下午9:33
 * Author：boni
 */

package com.wisfarm.gateway.filter;

import com.wisfarm.gateway.constants.SecurityConstants;
import com.wisfarm.gateway.properties.PermitAllUrlProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

@Slf4j
@Component
public class HeaderEnhanceFilter extends ZuulFilter {
    private static final String ANONYMOUS_USER_ID = "d4a65d04-a5a3-465c-8408-405971ac3346";

    @Autowired
    private PermitAllUrlProperties permitAllUrlProperties;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest servletRequest = ctx.getRequest();
        String authorization = servletRequest.getHeader("Authorization");
        String requestURI = servletRequest.getRequestURI();
        // test if request url is permit all , then remove authorization from header
        log.info(String.format("Enhance request URI : %s.", requestURI));
        if (isPermitAllUrl(requestURI) && isNotOAuthEndpoint(requestURI)) {
            HttpServletRequest resetRequest = removeValueFromRequestHeader((HttpServletRequest) servletRequest);
        } else if (StringUtils.isNotEmpty(authorization)) {
            if (isJwtBearerToken(authorization)) {
                try {
                    authorization = StringUtils.substringBetween(authorization, ".");
                    String decoded = new String(Base64.decodeBase64(authorization));

                    Map properties = new ObjectMapper().readValue(decoded, Map.class);

                    String userId = (String) properties.get(SecurityConstants.USER_ID_IN_HEADER);

                    ctx.addZuulRequestHeader(SecurityConstants.USER_ID_IN_HEADER, userId);
                } catch (Exception e) {
                    log.error("Failed to customize header for the request, but still release it as the it would be regarded without any user details.", e);
                }
            }
        } else {
            log.info("Regard this request as anonymous request, so set anonymous user_id in the header.");
            ctx.addZuulRequestHeader(SecurityConstants.USER_ID_IN_HEADER, ANONYMOUS_USER_ID);
        }
        return null;
    }

    private boolean isJwtBearerToken(String token) {
        return StringUtils.countMatches(token, ".") == 2 && (token.startsWith("Bearer") || token.startsWith("bearer"));
    }

    private boolean isNotOAuthEndpoint(String requestURI) {
        return !requestURI.contains("/login");
    }

    private HttpServletRequestWrapper removeValueFromRequestHeader(HttpServletRequest request) {
        HttpServletRequestWrapper httpServletRequestWrapper = new HttpServletRequestWrapper(request) {
            private Set<String> headerNameSet;
            private Set<String> headerSet;

            @Override
            public Enumeration<String> getHeaderNames() {
                if (headerNameSet == null) {
                    // first time this method is called, cache the wrapped request's header names:
                    headerNameSet = new HashSet();
                    Enumeration<String> wrappedHeaderNames = super.getHeaderNames();
                    while (wrappedHeaderNames.hasMoreElements()) {
                        String headerName = wrappedHeaderNames.nextElement();
                        if (!"Authorization".equalsIgnoreCase(headerName)) {
                            headerNameSet.add(headerName);
                        }
                    }
                    //set default header name value of tenant id and user id
                    headerNameSet.add(SecurityConstants.USER_ID_IN_HEADER);
                }

                return Collections.enumeration(headerNameSet);
            }

            @Override
            public Enumeration<String> getHeaders(String name) {

                if ("Authorization".equalsIgnoreCase(name)) {
                    return Collections.emptyEnumeration();
                }
                if (SecurityConstants.USER_ID_IN_HEADER.equalsIgnoreCase(name)) {
                    headerSet = new HashSet();
                    headerSet.add(ANONYMOUS_USER_ID);
                    return Collections.enumeration(headerSet);
                }
                return super.getHeaders(name);
            }

            @Override
            public String getHeader(String name) {
                if ("Authorization".equalsIgnoreCase(name)) {
                    return null;
                }
                if (SecurityConstants.USER_ID_IN_HEADER.equalsIgnoreCase(name)) {
                    return ANONYMOUS_USER_ID;
                }
                return super.getHeader(name);
            }
        };
        return httpServletRequestWrapper;
    }

    private boolean isPermitAllUrl(String url) {
        return permitAllUrlProperties.isPermitAllUrl(url);
    }

}
