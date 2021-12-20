package com.chenzx.movie.config.security;

import com.chenzx.movie.entity.sys.PathRoleMapping;
import com.chenzx.movie.service.sys.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/15 10:01
 */
@Component
public class FilterInvocationSecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {

    private final SysUserServiceImpl sysUserService;
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Value(value = "${security.login-url}")
    private String userLogin;

    public FilterInvocationSecurityMetadataSourceImpl(SysUserServiceImpl sysUserService) {
        this.sysUserService = sysUserService;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        if (userLogin.equals(requestUrl)) {
            return null;
        }
        List<PathRoleMapping> pathRoleMappings = sysUserService.queryPathRequiredRole();
        for (PathRoleMapping pathRoleMapping : pathRoleMappings) {
            if (antPathMatcher.match(pathRoleMapping.getPath(), requestUrl)) {
                List<String> roles = pathRoleMapping.getRoles();
                String[] roleArray = roles.toArray(new String[0]);
                return SecurityConfig.createList(roleArray);
            }
        }

        return SecurityConfig.createList("ROLE_Undefined");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
