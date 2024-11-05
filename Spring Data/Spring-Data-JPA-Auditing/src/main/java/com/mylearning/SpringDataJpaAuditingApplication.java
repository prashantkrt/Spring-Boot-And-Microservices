package com.mylearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class SpringDataJpaAuditingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaAuditingApplication.class, args);
    }

}
