package com.mylearning.multidb.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.mylearning.multidb.repository.db1",
        entityManagerFactoryRef = "db1EntityManagerFactory",
        transactionManagerRef = "db1TransactionManager"
)
public class DB1Config {

    /*
     *  Earlier Spring manages, now we have to manage it
     *
     *  1. DataSource
     *  2. EntityManager
     *  3. Transaction Management
     *
     * */

    // DataSource
    @Bean(name="db1DataSource")
    @Primary
    @ConfigurationProperties(prefix = "db1.datasource")
    public DataSource db1DataSource() {
        return DataSourceBuilder.create().build();
    }

    //EntityManager
    @Bean(name="db1EntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean db1EntityManagerFactory(EntityManagerFactoryBuilder entityManagerFactoryBuilder,@Qualifier("db1DataSource") DataSource dataSource) {
        HashMap<String, Object> properties = new HashMap<>();

        // commonly used props
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");


        return entityManagerFactoryBuilder.dataSource(dataSource)
                .packages("com.mylearning.multidb.model.customerModel") // my entity location
                .properties(properties)
                .persistenceUnit("Devlopment") // db name
                .build();
    }

    //Transaction Management
    @Bean(name="db1TransactionManager")
    @Primary
    public PlatformTransactionManager db1TransactionManager(
            @Qualifier("db1EntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}



