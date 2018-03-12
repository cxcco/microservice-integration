package com.blueskykong.auth.config.oauth;

import com.blueskykong.auth.security.provider.CustomAuthenticationProvider;
import com.blueskykong.auth.security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

//必须使用EnableWebSecurity才可以被自动注入
@Configuration
@EnableWebSecurity
public class AuthenticationManagerConfig extends GlobalAuthenticationConfigurerAdapter {

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        CustomAuthenticationProvider customAuthenticationProvider = new CustomAuthenticationProvider();
        CustomUserDetailsService customUserDetailsService = new CustomUserDetailsService();
        customAuthenticationProvider.setUserDetailsService(customUserDetailsService);

        auth.authenticationProvider(customAuthenticationProvider);
    }

}
