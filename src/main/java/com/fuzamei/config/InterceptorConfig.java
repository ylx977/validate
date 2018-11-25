package com.fuzamei.config;

import com.fuzamei.interceptor.TestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lmm
 * @description
 * @create 2018/9/26 19:35
 */

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private final TestInterceptor testInterceptor;

    @Autowired
    public InterceptorConfig(TestInterceptor testInterceptor) {
        this.testInterceptor = testInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(testInterceptor)
                .addPathPatterns("/hello/helloInterceptor");

    }
}
