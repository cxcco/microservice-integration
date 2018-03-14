/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：CustomUserDetails.java
 * Date：18-3-13 下午1:08
 * Author：boni
 */

package com.blueskykong.auth.security.extendsclz;

import com.blueskykong.auth.dto.UserClientIdDTO;
import com.blueskykong.auth.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author keets
 * @date 2017/8/5
 */
@Data
public class CustomUserDetails implements UserDetails {

    private static Long serialVersionUID = -7588980448693010309L;

    private Long id;

    private String username;

    private String password;

    private boolean enabled = true;

    private String userId;

    private String clientId;

    private String email;

    private String mobilePhone;

    private Collection<? extends GrantedAuthority> authorities;

    private List<String> roles = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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

    public static class CustomUserDetailsBuilder {
        private CustomUserDetails userDetails = new CustomUserDetails();

        public CustomUserDetailsBuilder withRoles(Collection<? extends GrantedAuthority> authorities) {
            userDetails.roles.addAll(authorities.stream().map((Function<GrantedAuthority, String>) GrantedAuthority::getAuthority).collect(Collectors.toList()));
            return this;
        }

        public CustomUserDetailsBuilder withUserObject(User user) {
            userDetails.setUsername(user.getUserName());
            userDetails.setPassword(user.getPassword());
            userDetails.setUserId(user.getUserId());
            userDetails.setEmail(user.getEmail());
            userDetails.setMobilePhone(user.getMobilePhone());
            userDetails.setId(user.getId());
           // userDetails.setClientId(user.getClientId());
            return this;
        }

        public CustomUserDetailsBuilder withAuthority(Collection<? extends GrantedAuthority> authorities) {
            userDetails.setAuthorities(authorities);
            return this;
        }

        public CustomUserDetails build() {
            return userDetails;
        }
    }

}
