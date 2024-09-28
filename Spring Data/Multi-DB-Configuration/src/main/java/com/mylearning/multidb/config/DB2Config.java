package com.mylearning.multidb.config;


import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DB2Config {
    /*
     *  Earlier Spring manages, now we have to manager it
     *
     *  1. DataSource
     *  2. EntityManager
     *  3. Transaction Management
     *
     * */

    // DataSource
    @Bean
    @ConfigurationProperties(prefix = "db2.datasource")
    public DataSource db2DataSource() {
        return DataSourceBuilder.create().build();
    }

    //EntityManager
    @Bean
    public LocalContainerEntityManagerFactoryBean db2EntityManagerFactory(EntityManagerFactoryBuilder entityManagerFactoryBuilder) {

        Map<String, Object> db2Properties = new HashMap<>();

        // commonly used props
        db2Properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
        db2Properties.put("hibernate.hbm2ddl.auto", "UPDATE");
        db2Properties.put("hibernate.show_sql", "true");
        db2Properties.put("hibernate.format_sql", "true");

        return entityManagerFactoryBuilder.dataSource(db2DataSource())
                .packages("com.mylearning.multidb.model")
                .properties(db2Properties)
                .persistenceUnit("postgres")
                .build();
    }

    //Transaction Management
    @Bean
    public PlatformTransactionManager db2TransactionManager(
            @Qualifier("db2EntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
