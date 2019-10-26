package com.aton.hack.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HereConfig {
    @Bean
    @ConfigurationProperties(prefix = "here.api.app")
    public HereApplicationParams hereApplicationParams() {
        return new HereApplicationParams();
    }

    @Bean
    @ConfigurationProperties(prefix = "here.api.hosts")
    public HereHostsParams hereHostsParams() {
        return new HereHostsParams();
    }
}
