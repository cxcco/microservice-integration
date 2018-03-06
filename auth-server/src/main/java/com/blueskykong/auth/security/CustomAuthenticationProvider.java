package com.blueskykong.auth.security;

import com.auth0.jwt.internal.org.apache.commons.lang3.StringUtils;
import com.blueskykong.auth.security.service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author keets
 * @date 2017/8/5
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AuthenticationUserDetailsService<AbstractAuthenticationToken> authenticationUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password;
        Map<String, String> data = (Map<String, String>) authentication.getDetails();
        String clientId = (String) data.get("client");
        Assert.hasText(clientId, "clientId must have value");
        String type = (String) data.get("type");
        Map map;

        password = (String) authentication.getCredentials();
        //如果你是调用user服务，这边不用注掉

        // map = userClient.checkUsernameAndPassword(getUserServicePostObject(username, password, type));
        map = checkUsernameAndPassword(getUserServicePostObject(username, password, type));


        String userId = (String) map.get("userId");
        if (StringUtils.isBlank(userId)) {
            String errorCode = (String) map.get("code");
            throw new BadCredentialsException(errorCode);
        }
        CustomUserDetails customUserDetails = buildCustomUserDetails(username, password, userId, clientId);
        return new CustomAuthenticationToken(customUserDetails);
    }

    /* private AbstractAuthenticationToken authenticateNow(final Authentication authentication)
             throws AuthenticationException {
         try {
             final UserDetails userDetails = loadUserByAssertion(assertion);
             userDetailsChecker.check(userDetails);
             return new CasAuthenticationToken(this.key, userDetails,
                     authentication.getCredentials(),
                     authoritiesMapper.mapAuthorities(userDetails.getAuthorities()),
                     userDetails, assertion);
         }
         catch (final TicketValidationException e) {
             throw new BadCredentialsException(e.getMessage(), e);
         }
     }
     private UserDetails loadUserBy*/
    private CustomUserDetails buildCustomUserDetails(String username, String password, String userId, String clientId) {
        CustomUserDetails customUserDetails = new CustomUserDetails.CustomUserDetailsBuilder()
                .withUserId(userId)
                .withPassword(password)
                .withUsername(username)
                .withClientId(clientId)
                .build();
        return customUserDetails;
    }

    private Map<String, String> getUserServicePostObject(String username, String password, String type) {
        Map<String, String> requestParam = new HashMap<String, String>();
        requestParam.put("userName", username);
        requestParam.put("password", password);
        if (type != null && StringUtils.isNotBlank(type)) {
            requestParam.put("type", type);
        }
        return requestParam;
    }

    //模拟调用user服务的方法
    private Map checkUsernameAndPassword(Map map) {

        //checkUsernameAndPassword
        Map ret = new HashMap();
        ret.put("userId", UUID.randomUUID().toString());

        return ret;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

}