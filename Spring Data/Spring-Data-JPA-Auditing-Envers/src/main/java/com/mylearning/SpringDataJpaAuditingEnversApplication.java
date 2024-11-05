package com.mylearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class) // for data envers
public class SpringDataJpaAuditingEnversApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaAuditingEnversApplication.class, args);
    }

}
