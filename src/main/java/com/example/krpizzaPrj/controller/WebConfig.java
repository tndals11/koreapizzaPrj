package com.example.krpizzaPrj.controller;


import com.example.krpizzaPrj.interceptor.SessionCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) { //인터셉터 등록
        registry.addInterceptor(new SessionCheckInterceptor())
                .addPathPatterns("/user/**")
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/common/**", "/css/**", "/fragment/**", "/images/**", "/upload/**", "/admin/**" );
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/images/")
                .addResourceLocations("classpath:/css/")
                .addResourceLocations("file:src/main/resources/static/upload/");
    }

}
