package com.snapp.spendtracker.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
@EnableCaching
public class BeanConfiguration {
    @Bean
    @RequestScope
    public RequestInfo requestInfo() {
        return new RequestInfo();
    }
}
