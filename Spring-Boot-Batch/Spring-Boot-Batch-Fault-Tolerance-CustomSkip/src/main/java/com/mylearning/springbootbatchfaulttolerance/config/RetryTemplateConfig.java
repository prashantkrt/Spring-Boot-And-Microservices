package com.mylearning.springbootbatchfaulttolerance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.support.RetryTemplate;

// way 2 is pretty simple
@Configuration
public class RetryTemplateConfig {

    @Bean(name="retryTemplate1")
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setRetryPolicy(new MyRetryPolicy(3));  // Set custom retry policy (max 3 retries)
        return retryTemplate;
    }
}
