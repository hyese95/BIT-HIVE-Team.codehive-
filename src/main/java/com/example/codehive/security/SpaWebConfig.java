package com.example.codehive.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpaWebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // /api/ 가 아닌 모든 경로(React 라우트)를 index.html 로 매핑
        registry.addViewController("/{spring:[^.]*}")
                .setViewName("forward:/index.html");
        registry.addViewController("//{spring:[^.]*}")
                .setViewName("forward:/index.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // static 내부 파일(js, css, 이미지 등)은 그대로 내보낸다
        registry
                .addResourceHandler("/**/*.js", "/**/*.css", "/**/*.html",
                        "/**/*.png","/**/*.svg","/**/*.ico")
                .addResourceLocations("classpath:/static/");
    }
}
