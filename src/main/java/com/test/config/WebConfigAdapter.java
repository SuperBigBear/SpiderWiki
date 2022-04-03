package com.test.config;

import com.test.config.ApiInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigAdapter implements WebMvcConfigurer {
    @Autowired
    ApiInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //intercept api request test
        registry.addInterceptor(interceptor).addPathPatterns("/**");
        //registry.addInterceptor(interceptor).addPathPatterns("/api/**");
    }
}
