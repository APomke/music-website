package com.lidong.music.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 指定哪些路径需要跨域处理
                .allowedOriginPatterns("*") // 允许哪些源访问你的应用
                .allowCredentials(true) // 是否允许凭证,必须配置这个才能获取到跨域请求携带的cookie
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 允许哪些HTTP方法
                .maxAge(3600); // 预检请求的有效期
    }
}