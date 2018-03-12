package com.blueskykong.auth.security;

import com.blueskykong.auth.security.service.CustomUserDetails;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by keets on 2017/8/5.
 */
public class CustomAuthenticationToken extends AbstractAuthenticationToken {
    static final long serialVersionUID = 8336497699509590280L;

    //private CustomUserDetails userDetails;
    private UserDetails userDetails;

    public CustomAuthenticationToken(UserDetails userDetails) {
        super(null);
        this.userDetails = userDetails;
        super.setAuthenticated(true);
    }

    public Object getPrincipal() {
        return this.userDetails;
    }

    public Object getCredentials() {
        return this.userDetails.getPassword();
    }

}