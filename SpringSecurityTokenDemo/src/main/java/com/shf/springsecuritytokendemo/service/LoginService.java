package com.shf.springsecuritytokendemo.service;

import com.shf.springsecuritytokendemo.domain.ResponseResult;
import com.shf.springsecuritytokendemo.domain.User;

public interface LoginService {
    ResponseResult login(User user);
}
