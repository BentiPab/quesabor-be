package com.queempanadas.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.queempanadas.security.JwtAuthorizationFilter;
import com.queempanadas.security.JwtUtil;
import com.queempanadas.services.CustomUserDetailsService;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
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
    public JwtParser jwtParser() {
        return Jwts.parser()
                .setSigningKey(env.getProperty("security.jwt.secret-key"));
    }

    ;

    @Bean
    public ObjectMapper mapper() {
        return new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Bean
    public String jwtSecretKey() {
        return env.getProperty("security.jwt.secret-key");
    }

    @Bean
    public Long accesTokenValidity() {
        return Long.parseLong(env.getProperty("security.jwt.expiration-time"));
    }
}
