package com.shf.springsecuritytokendemo.service.impl;

import com.shf.springsecuritytokendemo.domain.LoginUser;
import com.shf.springsecuritytokendemo.domain.ResponseResult;
import com.shf.springsecuritytokendemo.domain.User;
import com.shf.springsecuritytokendemo.service.LoginService;
import com.shf.springsecuritytokendemo.utils.JwtUtil;
import com.shf.springsecuritytokendemo.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
//        AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

//        如果认证没通过，给出响应提示
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }

//        如果认证通过,使用userid生成一个jwt,jwt存入redis,返回响应结果
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);

        HashMap<String, String> map = new HashMap<>();
        map.put("token", jwt);

//        把完整的用户信息存入redis，userid作为key，user作为value
        redisCache.setCacheObject("login:"+userId,loginUser);

        return new ResponseResult(200,"登录成功",map);
    }
}
