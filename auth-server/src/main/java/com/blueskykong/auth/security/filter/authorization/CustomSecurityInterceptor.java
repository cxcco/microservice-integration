package com.blueskykong.auth.security.filter.authorization;


import com.blueskykong.auth.security.filter.authorization.CustomAccessDecisionManager;
import com.blueskykong.auth.security.filter.authorization.CustomFilterInvocationMetadataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;

import javax.annotation.PostConstruct;
import javax.servlet.*;
import java.io.IOException;


/**
 * @author keets
 */
@Slf4j
public class CustomSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {
    @Autowired
    private CustomFilterInvocationMetadataSource metadataSource;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomAccessDecisionManager decisionManager;

    @PostConstruct
    public void init() {
        super.setAccessDecisionManager(decisionManager);
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init in Security ");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("doFilter in Security ");

        FilterInvocation fi = new FilterInvocation(servletRequest, servletResponse, filterChain);
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            log.info("through filter");
            super.afterInvocation(token, null);
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return metadataSource;
    }
}

