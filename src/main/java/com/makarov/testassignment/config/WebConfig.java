package com.makarov.testassignment.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/static/css/**",
                "/static/img/**",
                "/webjars/**",
                "/jquery/**")
                .addResourceLocations(
                        "classpath:/static/css/",
                        "classpath:/static/img/",
                        "classpath:/META-INF/resources/webjars/",
                        "classpath:/META-INF/resources/jquery/").setCachePeriod(0);
    }
}