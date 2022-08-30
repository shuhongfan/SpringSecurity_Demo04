package com.token.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.token.domain.LoginUser;
import com.token.domain.User;
import com.token.mapper.MenuMapper;
import com.token.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/*
 *   @author:Nico
 *   @create date:2022/8/15 0015 10:20
 *   @mail:hjx674894982@gmail.com
 *
 *
 *
 *
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(queryWrapper);
        //如果没有查询到用户则抛出异常
        if(Objects.isNull(user))
        {
            throw new NullPointerException("用户名或密码错误");
        }
        else{

        }
        //查询对应权限信息
//        List<String> list = new ArrayList<>(Arrays.asList("test","admin"));
        List<String> list = menuMapper.selectPermsByUserId(user.getId());
        //把数据封装成UserDetails返回
        return new LoginUser(user,list);
    }
}
