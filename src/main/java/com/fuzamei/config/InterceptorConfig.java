package com.fuzamei.config;

import com.fuzamei.interceptor.I18nInterceptor;
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
    private final I18nInterceptor i18nInterceptor;

    @Autowired
    public InterceptorConfig(TestInterceptor testInterceptor,
                             TestInterceptor2 testInterceptor2,
                             I18nInterceptor i18nInterceptor) {
        this.testInterceptor = testInterceptor;
        this.testInterceptor2 = testInterceptor2;
        this.i18nInterceptor = i18nInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(testInterceptor)
                .addPathPatterns("/hello/helloInterceptor").order(1);
        registry.addInterceptor(testInterceptor2)
                .addPathPatterns("/hello/helloInterceptor2").order(2);
        registry.addInterceptor(i18nInterceptor)
                .addPathPatterns("/**").order(2);

    }
}
