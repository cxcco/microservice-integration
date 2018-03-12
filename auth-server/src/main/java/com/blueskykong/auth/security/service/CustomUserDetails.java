/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：CustomUserDetails.java
 * Date：18-3-8 上午7:44
 * Author：boni
 */

package com.blueskykong.auth.security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author keets
 * @date 2017/8/5
 */
public class CustomUserDetails implements UserDetails {

    private static Long serialVersionUID = -7588980448693010309L;

    private String username;

    private String password;

    private boolean enabled = true;

    private String userId;

    private String clientId;

    private Collection<? extends GrantedAuthority> authorities;

    private List<String> roles;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setRole(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getRoles() {
        return roles;
    }

    public static class CustomUserDetailsBuilder {
        private CustomUserDetails userDetails = new CustomUserDetails();

        public CustomUserDetailsBuilder withRoles(Collection<? extends GrantedAuthority> authorities) {
            userDetails.roles.addAll(authorities.stream().map((Function<GrantedAuthority, String>) GrantedAuthority::getAuthority).collect(Collectors.toList()));
            return this;
        }

        public CustomUserDetailsBuilder withUsername(String username) {
            userDetails.setUsername(username);
            userDetails.setAuthorities(null);
            return this;
        }

        public CustomUserDetailsBuilder withPassword(String password) {
            userDetails.setPassword(password);
            return this;
        }

        public CustomUserDetailsBuilder withClientId(String clientId) {
            userDetails.setClientId(clientId);
            return this;
        }

        public CustomUserDetailsBuilder withUserId(String userId) {
            userDetails.setUserId(userId);
            return this;
        }

        public CustomUserDetails build() {
            return userDetails;
        }
    }

}
