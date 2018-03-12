package com.blueskykong.auth.security.provider;

import com.auth0.jwt.internal.org.apache.commons.lang3.StringUtils;
import com.blueskykong.auth.security.CustomAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Map;

/**
 * 在AuthenticationManagerConfig中被注入，通过AuthenticationUserDetailsService加载UserDtails对象
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {

    UserDetailsService userDetailsService;

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String userPass = (String) authentication.getCredentials();
        Map details = (Map) authentication.getDetails();
        String type = (String) details.get("grant_type");
        String client = (String) details.get("client");
        String paramString = username + ":" + userPass + ":" + type + ":" + client;
        UserDetails customUserDetails = userDetailsService.loadUserByUsername(paramString);
        //String userId = customUserDetails.getUserId();
        String name = customUserDetails.getUsername();
        if (StringUtils.isBlank(name)) {
            Map data = (Map) authentication.getDetails();
            String errorCode = (String) data.get("code");
            throw new BadCredentialsException(errorCode);
        }
        return new CustomAuthenticationToken(customUserDetails);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

}