package com.kawakawaryuryu.samplespringbatch.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;

@Configuration
public class WebConfig {

    @Bean
    public RestOperations restOperations() {
        return new RestTemplateBuilder()
                .build();
    }
}
