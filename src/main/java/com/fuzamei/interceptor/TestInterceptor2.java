package com.fuzamei.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by fuzamei on 2018/11/14.
 */
@Slf4j
@Component
public class TestInterceptor2 extends HandlerInterceptorAdapter {
    private static final String OPTIONS = "OPTIONS";

    private static final String TOKEN = "token";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("预处理方法");
        request.setAttribute("haha","yes");
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandler方法");
        Object haha = request.getAttribute("haha");
        log.info("postHandler方法:"+haha);
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion方法");
        Object haha = request.getAttribute("haha");
        log.info("afterCompletion方法:"+haha);
        super.afterCompletion(request, response, handler, ex);
    }
}