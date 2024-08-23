package com.billcom.eshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Allow all paths
                .allowedOrigins("*")  // Allow this origin
                .allowedMethods("*")  // Allow these methods
                .allowedHeaders("*")  // Allow these headers
                .maxAge(36000);  // Cache preflight response for 1 hour
    }
}
