package com.shf.springsecurityquickstart.config;

import com.shf.springsecurityquickstart.handler.MyLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;


    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
//                配置认证失败处理器
                .successHandler(authenticationSuccessHandler)
//                配置认证失败处理器
                .failureHandler(authenticationFailureHandler);

        http.logout()
//                配置注销成功处理器
                        .logoutSuccessHandler(logoutSuccessHandler);


        http.authorizeRequests()
                .anyRequest().authenticated();
    }
}
