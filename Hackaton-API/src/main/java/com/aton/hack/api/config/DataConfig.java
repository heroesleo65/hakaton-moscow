package com.aton.hack.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConfig {
    @Bean
    @ConfigurationProperties(prefix = "data")
    public DataParams dataParams() {
        return new DataParams();
    }
}
