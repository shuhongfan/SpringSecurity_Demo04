package com.token.handler;

import com.alibaba.fastjson.JSON;
import com.token.domain.ResponseResult;
import com.token.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 *   @author:Nico
 *   @create date:2022/8/16 0016 16:11
 *   @mail:hjx674894982@gmail.com
 *
 *
 *
 *
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(HttpStatus.UNAUTHORIZED.value(),"用户认证失败，请重新登录");
        String json = JSON.toJSONString(result);
        //处理异常
        WebUtils.renderString(response,json);
    }
}
