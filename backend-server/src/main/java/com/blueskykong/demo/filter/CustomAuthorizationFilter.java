package com.blueskykong.demo.filter;

import com.blueskykong.demo.client.FeignAuthClient;
import com.blueskykong.demo.constants.AccessType;
import com.blueskykong.demo.constants.SecurityConstants;
import com.blueskykong.demo.entity.Permission;
import com.blueskykong.demo.security.SimpleGrantedAuthority;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author keets
 */
public class CustomAuthorizationFilter extends ZuulFilter {
    @Autowired
    private FeignAuthClient feignAuthClient;

    @Override
    public String filterType() {
        return null;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public Object run() {

        RequestContext context = RequestContext.getCurrentContext();
        String userId = context.getZuulRequestHeaders().get(SecurityConstants.USER_ID_IN_HEADER);

        if (StringUtils.isNotEmpty(userId)) {
            UserContext userContext = new UserContext(UUID.fromString(userId));
            userContext.setAccessType(AccessType.ACCESS_TYPE_NORMAL);
            System.out.println(userContext);
            List<Permission> permissionList = feignAuthClient.getUserPermissions(userId);
            List<SimpleGrantedAuthority> authorityList = new ArrayList();
            for (Permission permission : permissionList) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority();
                authority.setAuthority(permission.getPermission());
                authorityList.add(authority);
            }
            userContext.setAuthorities(authorityList);

            SecurityContextHolder.setContext(userContext);
        }
        return null;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }


}
