package com.queempanadas.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.queempanadas.security.JwtAuthorizationFilter;
import com.queempanadas.security.JwtUtil;
import com.queempanadas.services.CustomUserDetailsService;
import com.queempanadas.services.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ApplicationConfig {

    @Autowired
    Environment env;

    @Bean
    public CustomUserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter();
    }

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }


    @Bean
    public ObjectMapper mapper() {
        return new ObjectMapper().registerModule(new JavaTimeModule());
    }


    @Bean
    public Long accessTokenValidity() {
        return Long.parseLong(env.getProperty("security.jwt.expiration-time"));
    }

    @Bean
    public PromotionService promotionService() {
        return new PromotionService();
    }
}
