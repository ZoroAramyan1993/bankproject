package com.example.bank_aplication_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.*;


@Configuration
@EnableWebSecurity
@EnableWebMvc
@ComponentScan

public class WebMvcConfig extends WebMvcConfigurerAdapter {
    private final long MAX_AGE_SICS = 3600;

//    @Bean
//    WebMvcConfigurer myWebMvcConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//
//            @Override
//            public void addViewControllers(ViewControllerRegistry registry) {
//                ViewControllerRegistration r = registry.addViewController("/");
//                r.setViewName("forward:/index.html");
//            }
//        };
//    }


//
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**").allowedOrigins("*").
//                allowedMethods("HEAD","OPTIONS","GET","POST","PUT","PATCH","DELETE").maxAge(MAX_AGE_SICS);
//    }
}
