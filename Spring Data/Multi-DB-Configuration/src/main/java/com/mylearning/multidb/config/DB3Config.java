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


@Configuration
public class DB3Config {
    /*
     *  Earlier Spring manages, now we have to manager it
     *
     *  1. DataSource
     *  2. EntityManager
     *  3. Transaction Management
     *
     * */

    // DataSource
    @Bean(name="db3DataSource")
    @ConfigurationProperties(prefix = "db3.datasource")
    public DataSource db3DataSource() {
        return DataSourceBuilder.create().build();
    }

    //EntityManager
    @Bean(name="db3EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean db3EntityManagerFactory(EntityManagerFactoryBuilder entityManagerFactoryBuilder) {

        HashMap<String, Object> db3Properties = new HashMap<>();

        // commonly used props
        db3Properties.put("hibernate.hbm2ddl.auto", "UPDATE");
        db3Properties.put("hibernate.show_sql", "true");
        db3Properties.put("hibernate.format_sql", "true");
        db3Properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

        // adding some extra props
        db3Properties.put("hibernate.use_sql_comments", "true");
        db3Properties.put("hibernate.order_inserts", "true");
        db3Properties.put("hibernate.order_updates", "true");


        return entityManagerFactoryBuilder.dataSource(db3DataSource())
                .packages("com.mylearning.multidb.model")
                .properties(db3Properties)
                .persistenceUnit("myDB")
                .build();
    }

    //Transaction Management
    @Bean(name="db3TransactionManager")
    public PlatformTransactionManager db3TransactionManager(
            @Qualifier("db3EntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
