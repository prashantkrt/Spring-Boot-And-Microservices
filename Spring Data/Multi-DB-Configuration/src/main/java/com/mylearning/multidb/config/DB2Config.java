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
        basePackages = "com.mylearning.multidb.repository.db2",
        entityManagerFactoryRef = "db2EntityManagerFactory",
        transactionManagerRef = "db2TransactionManager"
)
public class DB2Config {

    // DataSource
    @Bean(name="db2DataSource")
    @ConfigurationProperties(prefix = "db2.datasource")
    public DataSource db2DataSource() {
        return DataSourceBuilder.create().build();
    }

    //EntityManager
    @Bean(name="db2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean db2EntityManagerFactory(EntityManagerFactoryBuilder entityManagerFactoryBuilder) {

        HashMap<String, Object> db2Properties = new HashMap<>();

        // commonly used props
        db2Properties.put("hibernate.hbm2ddl.auto", "update");
        db2Properties.put("hibernate.show_sql", "true");
        db2Properties.put("hibernate.format_sql", "true");
        db2Properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

        // adding some extra props
        db2Properties.put("hibernate.use_sql_comments", "true");
        db2Properties.put("hibernate.order_inserts", "true");
        db2Properties.put("hibernate.order_updates", "true");


        return entityManagerFactoryBuilder.dataSource(db2DataSource())
                .packages("com.mylearning.multidb.model.productModel")
                .properties(db2Properties)
                .persistenceUnit("newDB")
                .build();
    }

    //Transaction Management
    @Bean(name="db2TransactionManager")
    public PlatformTransactionManager db2TransactionManager(
            @Qualifier("db2EntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
