package com.shf.springsecuritytokendemo.handler;

import com.alibaba.fastjson.JSON;
import com.shf.springsecuritytokendemo.domain.ResponseResult;
import com.shf.springsecuritytokendemo.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 授权异常处理器
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(HttpStatus.FORBIDDEN.value(), "您的权限不足，请联系管理员");
        String json = JSON.toJSONString(result);

//        处理异常
        WebUtils.renderString(response,json);
    }
}
