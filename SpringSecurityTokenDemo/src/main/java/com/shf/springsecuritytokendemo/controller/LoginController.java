package com.shf.springsecuritytokendemo.controller;

import com.shf.springsecuritytokendemo.domain.ResponseResult;
import com.shf.springsecuritytokendemo.domain.User;
import com.shf.springsecuritytokendemo.service.LoginService;
import com.shf.springsecuritytokendemo.service.LogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private LogoutService logoutService;

    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user) {
        return loginService.login(user);
    }

    @PostMapping("/user/logout")
    public ResponseResult logout() {
        return logoutService.logout();
    }
}
