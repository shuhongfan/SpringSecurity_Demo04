package com.token.service;

import com.token.domain.ResponseResult;
import com.token.domain.User;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
