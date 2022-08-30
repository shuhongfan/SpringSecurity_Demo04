package com.shf.springsecuritytokendemo.service.impl;

import com.shf.springsecuritytokendemo.domain.LoginUser;
import com.shf.springsecuritytokendemo.domain.ResponseResult;
import com.shf.springsecuritytokendemo.service.LogoutService;
import com.shf.springsecuritytokendemo.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LogoutServiceImpl implements LogoutService {
    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult logout() {
//        获取SecurityContextHolder中的用户id
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();

//        删除redis中的值
        redisCache.deleteObject("login:" + userId);
        return new ResponseResult(200,"注销成功");
    }
}

