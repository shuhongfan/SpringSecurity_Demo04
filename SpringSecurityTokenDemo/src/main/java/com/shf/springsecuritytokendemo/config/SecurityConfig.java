package com.shf.springsecuritytokendemo.config;

import com.shf.springsecuritytokendemo.Filter.JwtAuthenticationTokenFilter;
import com.shf.springsecuritytokendemo.handler.AccessDeniedHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     *  默认使用的PasswordEncoder要求数据库中的密码格式为：{id}password 。
     *  它会根据id去判断密码的加密方式。但是我们一般不会采用这种方式。
     *  所以就需要替换PasswordEncoder。
     *
     *  我们一般使用SpringSecurity为我们提供的BCryptPasswordEncoder。
     *  我们只需要使用把BCryptPasswordEncoder对象注入Spring容器中，SpringSecurity就会使用该PasswordEncoder来进行密码校验。
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AccessDeniedHandlerImpl accessDeniedHandler;

    /**
     * 认证
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                关闭csrf
                .csrf().disable()
//                不通过session获取securityManager
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/hello").permitAll()
//                基于配置的权限控制
                .antMatchers("/testCors").hasAnyAuthority("system:dept:list")

//                对于登录接口，允许匿名访问
                .antMatchers("/user/login").anonymous()
//                除上面外的所有请求都需要身份认证
                .anyRequest().authenticated();

//        添加JWT过滤器，在UsernamePasswordAuthenticationFilter之前添加
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

//        配置异常处理器
        http.exceptionHandling()
//                认证失败处理器
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);

//        开启跨域
        http.cors();
    }

    /**
     * 认证管理器
     * @return
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
