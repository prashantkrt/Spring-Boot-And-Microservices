package com.mylearning.productservice.firstproject.condition;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfig {

    // will check if the prod.dataSource.value = true in application.properties then will create it's bean
    @Bean
    @ConditionalOnProperty(name = "prod.dataSource.value", havingValue = "true")
    @Primary
    public DataSourceConfig enableProdDataSource() {
        return new EnableProdDataSource();
    }

    // if the bean for EnableProdDataSource not created then create bean for Dev
    @Bean
    @ConditionalOnMissingBean(EnableProdDataSource.class)
    public DataSourceConfig enableDevDataSource() {
        return new EnableDevDataSource();
    }
}
