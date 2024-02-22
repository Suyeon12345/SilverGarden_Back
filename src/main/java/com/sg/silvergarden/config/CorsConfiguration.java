package com.sg.silvergarden.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // 모든 요청에 대해서
                                .allowedMethods("PUT", "DELETE", "POST", "GET")
                                .allowedOrigins("http://localhost:8000/", "http://localhost:3000/"); // 허용할 오리진들
                // .allowedOrigins("*");
        }
}
