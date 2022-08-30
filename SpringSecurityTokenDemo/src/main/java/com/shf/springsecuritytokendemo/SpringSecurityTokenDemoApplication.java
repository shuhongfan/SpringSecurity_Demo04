package com.shf.springsecuritytokendemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 用户登录流程
 * 1.提交用户名密码
 * 2.封装Authentication对象，这时候最多只有用户名和密码，权限还没有
 * 3.调用Authentication方法进行认证
 * 4.调用DaoAuthenticationProvider的authentication方法进行认证
 * 5.调用loadUserByUsername方法查询用户
 *  5.1 根据用户名去查询对应的用户和这个用户对应的权限信息，InMemoryDetailsManager是在内存中查找
 *  5.2 把对应的用户信息包括权限信息封装成UserDetail对象
 * 6.返回UserDetail对象
 * 7.通过PasswordEncoder对比UserDetail中的密码和Authentication的密码是否正确
 * 8.如果正确就把UserDetail中的权限信息设置到Authentication对象中，并且使用用户id生成一个jwt，然后用userid作为key，用户信息作为value存入redis
 * 9.返回Authentication对象
 * 10.如果上一步返回了Authentication对象就使用SecurityContextHolder.getContent().setAuthentication(authentication)方法存储对象，
 * 其他过滤器通过SecurityContextHolder来获取当前用户信息
 *
 *
 * 用户认证过滤器 JwtAuthenticationTokenFilter
 * 1.获取token
 * 2.解析token
 * 3.获取userid
 * 4.封装Authentication对象存入SecurityContextHolder
 * 5.从SecurityContextHolder中获取当前请求用户信息
 *
 *
 * 思路分析
 * ① 登录 ===》
 *      自定义登录接口，
 *          调用ProviderManager的方法进行认证，如果认证通过生成jwt
 *          把用户信息存入redis中
 *      自定义UserDetailService
 *          在这个实现列中去查询数据库
 *
 * ② 校验 ===》 定义JWT认证过滤器
 *      获取token
 *      解析token获取其中的userid
 *      从redis中获取用户信息
 *      存入SecurityContextHolder
 *
 * 用户名 admin
 * 密码 123456
 */
@SpringBootApplication
@MapperScan("com.shf.springsecuritytokendemo.mapper")
@ComponentScan("com.shf")
public class SpringSecurityTokenDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityTokenDemoApplication.class, args);
    }

}
