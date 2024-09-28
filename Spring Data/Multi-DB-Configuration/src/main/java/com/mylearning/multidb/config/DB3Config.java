package com.mylearning.multidb.config;


import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {
                "com.mylearning.multidb.repository"
        },
        entityManagerFactoryRef = "db3EntityManagerFactory",
        transactionManagerRef = "db3TransactionManager"
)
public class DB3Config {

    // DataSource
    @Bean(name="db3DataSource")
    @ConfigurationProperties(prefix = "db3.datasource")
    public DataSource db3DataSource() {
        return DataSourceBuilder.create().build();
    }

    //EntityManager
    @Bean(name="db3EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean db3EntityManagerFactory(EntityManagerFactoryBuilder entityManagerFactoryBuilder) {

        Map<String, Object> db3Properties = new HashMap<>();

        // commonly used props
        db3Properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
        db3Properties.put("hibernate.hbm2ddl.auto", "UPDATE");
        db3Properties.put("hibernate.show_sql", "true");
        db3Properties.put("hibernate.format_sql", "true");

        return entityManagerFactoryBuilder.dataSource(db3DataSource())
                .packages("com.mylearning.multidb.model.userModel")
                .properties(db3Properties)
                .persistenceUnit("postgres")
                .build();
    }

    //Transaction Management
    @Bean(name="db3TransactionManager")
    public PlatformTransactionManager db3TransactionManager(
            @Qualifier("db3EntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
