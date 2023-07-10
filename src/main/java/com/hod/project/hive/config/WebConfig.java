package com.hod.project.hive.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private TokenAuthInterceptor tokenAuthInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenAuthInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/login");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 또는 필요한 URL 패턴
              .allowedOrigins("*") // 허용할 도메인, 필요에 따라 수정
              .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메서드, 필요에 따라 수정
              .allowedHeaders("*"); // 허용할 헤더, 필요에 따라 수정
    }
}
