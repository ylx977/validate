package com.fuzamei.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by fuzamei on 2018/11/14.
 */
@Slf4j
@Component
public class TestInterceptor extends HandlerInterceptorAdapter {
    private static final String OPTIONS = "OPTIONS";

    private static final String TOKEN = "token";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("进入前台用户权限验证拦截器");

        //跳过预处理
        if (OPTIONS.equals(request.getMethod()) || request.getRequestURI().toLowerCase().contains(TOKEN)) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        log.info("过了option");

        return super.preHandle(request, response, handler);
    }
}