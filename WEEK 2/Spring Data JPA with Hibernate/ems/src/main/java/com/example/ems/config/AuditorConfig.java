package com.example.ems.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * Exercise 7: Enabling Entity Auditing.
 * In a real app this would pull the current user from the security context.
 * Here it returns a fixed "system" auditor so @CreatedBy / @LastModifiedBy work out of the box.
 */
@Configuration
public class AuditorConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.of("system");
    }
}
