package com.chenzx.movie.config.security;

import com.chenzx.movie.entity.sys.GlobalResults;
import com.chenzx.movie.utils.GlobalResultsStatusCodeEnum;
import com.chenzx.movie.utils.GlobalResultsUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/14 14:26
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value(value = "${security.login-url}")
    private String userLoginUrl;
    @Value(value = "${security.logout-url}")
    private String userLogoutUrl;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;
    @Autowired
    private AccessDecisionManager accessDecisionManager;

    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        //此处可添加别的规则,目前只设置 允许双 //
        firewall.setAllowUrlEncodedDoubleSlash(true);
        return firewall;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(filterInvocationSecurityMetadataSource);
                        object.setAccessDecisionManager(accessDecisionManager);
                        return object;
                    }
                })
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl(userLoginUrl)
                .successHandler((req, resp, authentication) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    resp.getWriter().write(new ObjectMapper().writeValueAsString(GlobalResultsUtil.isOk(authentication.getAuthorities())));
                })
                .failureHandler((req, resp, exception) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    GlobalResults globalResults;
                    if (exception instanceof LockedException) {
                        globalResults = GlobalResultsUtil.error(GlobalResultsStatusCodeEnum
                                .SECURITY_ACCOUNT_LOCKED.getValue(), "账户被锁定", null);
                    } else if (exception instanceof DisabledException) {
                        globalResults = GlobalResultsUtil.error(GlobalResultsStatusCodeEnum
                                .SECURITY_ACCOUNT_DISABLE.getValue(), "账户被禁用", null);
                    } else if (exception instanceof BadCredentialsException) {
                        globalResults = GlobalResultsUtil.error(GlobalResultsStatusCodeEnum
                                .SECURITY_ACCOUNT_USERNAME_PASSWORD_ERROR.getValue(), "用户名或者密码输入错误", null);
                    } else {
                        globalResults = GlobalResultsUtil.error(GlobalResultsStatusCodeEnum
                                .FAIL.getValue(), "认证服务错误,请联系管理员", null);
                    }
                    resp.getWriter().write(new ObjectMapper().writeValueAsString(globalResults));

                })
                .permitAll()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint((req, resp, authException) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    resp.getWriter().write(new ObjectMapper()
                            .writeValueAsString(GlobalResultsUtil
                                    .error(GlobalResultsStatusCodeEnum.SECURITY_LOGIN_REQUIRED.getValue()
                                            , "需要登录", null)));
                })
                .accessDeniedHandler((req, resp, accessDeniedException) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    resp.getWriter().write(new ObjectMapper()
                            .writeValueAsString(GlobalResultsUtil
                                    .error(GlobalResultsStatusCodeEnum.SECURITY_NO_ACCESS.getValue()
                                            , "无权限访问", null)));
                })
                .and()
                .logout()
                .logoutUrl(userLogoutUrl)
                .logoutSuccessHandler((req, resp, authentication) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    resp.getWriter().write(new ObjectMapper().writeValueAsString(GlobalResultsUtil.isOk("退出登录成功")));
                })
                .deleteCookies("JSESSIONID", "movie_account")
                .and()
                .csrf().disable()
                .cors().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("index.html", "favicon.ico", "/static/**");
    }
}
