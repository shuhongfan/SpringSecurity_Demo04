package com.shf.springsecuritytokendemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shf.springsecuritytokendemo.domain.LoginUser;
import com.shf.springsecuritytokendemo.domain.Menu;
import com.shf.springsecuritytokendemo.domain.User;
import com.shf.springsecuritytokendemo.mapper.MenuMapper;
import com.shf.springsecuritytokendemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        根据用户名查询用户信息
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName, username);
        User user = userMapper.selectOne(wrapper);

//        如果查询不到数据就通过抛出异常来给出提示
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

//        TODO 查询对应的权限信息
//        ArrayList<String> list = new ArrayList<>(Arrays.asList("admin"));
        List<String> menus = menuMapper.selectPermsByUserId(user.getId());

//        封装成UserDetails对象返回
        return new LoginUser(user,menus);
    }
}
