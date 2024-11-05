package com.mylearning.config;

import com.mylearning.audits.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class AuditorAwareConfig {

    @Bean("auditorAwareImpl")
    public AuditorAware<String> getAuditorAware() {
        return new AuditorAwareImpl();
    }
}
