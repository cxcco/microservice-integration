/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：ResponseFilter.java
 * Date：18-3-14 下午12:50
 * Author：boni
 */

package com.blueskykong.gateway.filter;

import com.blueskykong.gateway.constants.SecurityConstants;
import com.blueskykong.gateway.properties.PermitAllUrlProperties;
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
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Slf4j
@Component
public class ResponseFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletResponse response = ctx.getResponse();
        log.info(String.valueOf(response.getStatus()));
        return null;
    }

}
