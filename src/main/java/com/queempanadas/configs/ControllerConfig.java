package com.queempanadas.configs;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.queempanadas.services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfig {

    @Bean
    public UserService userService() {
        return new UserService();
    }

    @Bean
    public EmpanadaService empanadaService() {
        return new EmpanadaService();
    }

    @Bean
    public EntryService entryService() {
        return new EntryService();
    }

    @Bean
    public SaleService saleService() {
        return new SaleService();
    }

    @Bean
    public QualityService qualityService() {
        return new QualityService();
    }

    @Bean
    public ObjectMapper mapper() {
        return new JsonMapper().registerModule(new JavaTimeModule());
    }
}
