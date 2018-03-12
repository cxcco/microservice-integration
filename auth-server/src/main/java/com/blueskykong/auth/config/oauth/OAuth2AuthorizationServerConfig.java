package com.blueskykong.auth.config.oauth;

import com.blueskykong.auth.security.convert.CustomJwtAccessTokenConvert;
import com.blueskykong.auth.security.service.CustomAuthorizationTokenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;

/**
 * Created by keets on 2017/9/25.
 */

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private static final String SIGN_KEY = "secret";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private WebResponseExceptionTranslator webResponseExceptionTranslator;

    @Bean
    public JdbcClientDetailsService clientDetailsService(DataSource dataSource) {
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    public JdbcTokenStore tokenStore(DataSource dataSource) {
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new CustomJwtAccessTokenConvert();
        converter.setSigningKey(SIGN_KEY);
        return converter;
    }

    @Bean
    public AuthorizationServerTokenServices authorizationServerTokenServices() {
        CustomAuthorizationTokenServices customTokenServices = new CustomAuthorizationTokenServices();
        customTokenServices.setTokenStore(tokenStore(dataSource));
        customTokenServices.setSupportRefreshToken(true);
        customTokenServices.setReuseRefreshToken(true);
        customTokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);//7days
        customTokenServices.setAccessTokenValiditySeconds(60 * 60 * 2); //2hours
        customTokenServices.setClientDetailsService(clientDetailsService(dataSource));
        customTokenServices.setTokenEnhancer(accessTokenConverter());
        return customTokenServices;
    }

    //使用SpEL表达式来配置TokenEndpoint的安全约束规则标准
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    //配置ClientDetailsService
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService(dataSource));
    }

    //配置授权（authorization）以及token访问端点和令牌服务(token services)
    /* 默认的path
            /oauth/authorize：授权端点。
            /oauth/token：令牌端点。
            /oauth/confirm_access：用户确认授权提交端点。
            /oauth/error：授权服务错误信息端点。
            /oauth/check_token：用于资源服务访问的令牌解析端点。
            /oauth/token_key：提供公有密匙的端点，如果你使用JWT令牌的话。
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(tokenStore(dataSource))
                .tokenServices(authorizationServerTokenServices())
                .accessTokenConverter(accessTokenConverter())
                .exceptionTranslator(webResponseExceptionTranslator);
    }
}
