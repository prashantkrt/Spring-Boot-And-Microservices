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

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.mylearning.multidb.model",
        entityManagerFactoryRef = "db1EntityManagerFactory",
        transactionManagerRef = "db1TransactionManager"
)
public class DB1Config {

    /*
     *  Earlier Spring manages, now we have to manager it
     *
     *  1. DataSource
     *  2. EntityManager
     *  3. Transaction Management
     *
     * */

    // DataSource
    @Bean(name="db1DataSource")
    @ConfigurationProperties(prefix = "db1.datasource")
    public DataSource db1DataSource() {
        return DataSourceBuilder.create().build();
    }

    //EntityManager
    @Bean(name="db1EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean db1EntityManagerFactory(EntityManagerFactoryBuilder entityManagerFactoryBuilder) {
        HashMap<String, Object> properties = new HashMap<>();

        // commonly used props
        properties.put("hibernate.hbm2ddl.auto", "UPDATE");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MYSQL8Dialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");

        // adding some extra props
        properties.put("hibernate.use_sql_comments", "true");
        properties.put("hibernate.order_inserts", "true");
        properties.put("hibernate.order_updates", "true");


        return entityManagerFactoryBuilder.dataSource(db1DataSource())
                .packages("com.mylearning.multidb.model") // my entity location
                .properties(properties)
                .persistenceUnit("DEV") // db name
                .build();
    }

    //Transaction Management
    @Bean(name="db1TransactionManager")
    public PlatformTransactionManager db1TransactionManager(
            @Qualifier("db1EntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}



