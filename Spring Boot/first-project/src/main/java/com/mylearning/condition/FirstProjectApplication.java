package com.mylearning.condition;

import com.mylearning.condition.condition.DataSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
* @SpringBootApplication is combination of
*  - @Configuration  => for @Bean creations or java based configurations
*  - @ComponentScan  =>  tell the spring boot to scan what all packages needed and load the packages to load what all provided
*  - @AutoConfiguration =>  for autoconfiguration of external dependencies needed will be checking for negative and positive conditions mentioned on the pom.xml
* */
@SpringBootApplication
public class FirstProjectApplication implements CommandLineRunner {

    @Autowired
    private DataSourceConfig dataSourceConfig;

    public static void main(String[] args) {
        SpringApplication.run(FirstProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        dataSourceConfig.makeConnection();
    }
}
