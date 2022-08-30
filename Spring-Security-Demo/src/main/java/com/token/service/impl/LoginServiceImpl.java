package com.token.service.impl;

import com.token.domain.LoginUser;
import com.token.domain.ResponseResult;
import com.token.domain.User;
import com.token.service.LoginService;
import com.token.utils.JwtUtil;
import com.token.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/*
 *   @author:Nico
 *   @create date:2022/8/15 0015 15:09
 *   @mail:hjx674894982@gmail.com
 *
 *
 *
 *
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        //AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(),
                        user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //认证未通过则给出对应提示
        if(Objects.isNull(authenticate))
        {
            throw new RuntimeException("登陆失败");
        }
        //认证通过，使用userid生成一个jwt，jwt存入ResponseResult返回
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userid = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userid);
        Map<String,String> map = new HashMap<>();
        map.put("token",jwt);
        //把完整的用户信息存入Redis，userid作为key。
        redisCache.setCacheObject("login:"+userid,loginUser);
        return new ResponseResult(200,"登录成功",map);
   }

    @Override
    public ResponseResult logout() {
        //获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken  authentication
                = (UsernamePasswordAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        //删除Redis中的值
        redisCache.deleteObject("login:"+userid);
        return new ResponseResult(200,"注销成功");

    }
}
