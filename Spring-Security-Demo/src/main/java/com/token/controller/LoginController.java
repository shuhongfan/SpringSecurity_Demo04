package com.token.controller;

import com.token.domain.ResponseResult;
import com.token.domain.User;
import com.token.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 *   @author:Nico
 *   @create date:2022/8/15 0015 15:04
 *   @mail:hjx674894982@gmail.com
 *
 *
 *
 *
 */
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping ("/user/login")
    public ResponseResult login(@RequestBody User user){
        //登录
        return loginService.login(user);
    }


    @RequestMapping("/user/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }
}
