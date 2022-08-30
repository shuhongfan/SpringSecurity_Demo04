package com.token.handler;

import com.alibaba.fastjson.JSON;
import com.token.domain.ResponseResult;
import com.token.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 *   @author:Nico
 *   @create date:2022/8/16 0016 16:23
 *   @mail:hjx674894982@gmail.com
 *
 *
 *
 *
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(HttpStatus.FORBIDDEN.value(),"权限不足！");
        String json = JSON.toJSONString(result);
        //处理异常
        WebUtils.renderString(response,json);
    }
}
