package com.blueskykong.auth.config.oauth;

import com.blueskykong.auth.security.authorization.provider.CustomAuthenticationProvider;
import com.blueskykong.auth.security.authorization.service.CustomUserDetailsService;
import com.blueskykong.auth.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;

//授权类型为password时使用该认证管理器
@Configuration
public class AuthenticationManagerConfig extends GlobalAuthenticationConfigurerAdapter {
    @Autowired
    CustomAuthenticationProvider customAuthenticationProvider;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }
}
