package com.shf.springsecuritytokendemo.expression;

import com.shf.springsecuritytokendemo.domain.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("ex")
public class MyExpressionRoot {
    public boolean hasAuthority(String authority) {
//        获取当前用户的权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        List<String> permissions = loginUser.getPermissions();

//        判断用户权限集合中是否存在authority
        boolean contains = permissions.contains(authority);
        return contains;
    }
}
