package com.fuzamei.config;

import com.fuzamei.interceptor.TestInterceptor;
import com.fuzamei.interceptor.TestInterceptor2;
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
    private final TestInterceptor2 testInterceptor2;

    @Autowired
    public InterceptorConfig(TestInterceptor testInterceptor,
                             TestInterceptor2 testInterceptor2) {
        this.testInterceptor = testInterceptor;
        this.testInterceptor2 = testInterceptor2;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(testInterceptor)
                .addPathPatterns("/hello/helloInterceptor").order(1);
        registry.addInterceptor(testInterceptor2)
                .addPathPatterns("/hello/helloInterceptor2").order(2);

    }
}
