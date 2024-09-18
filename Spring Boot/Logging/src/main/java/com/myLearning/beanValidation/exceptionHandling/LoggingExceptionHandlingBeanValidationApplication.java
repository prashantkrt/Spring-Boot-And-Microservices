package com.myLearning.beanValidation.exceptionHandling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoggingExceptionHandlingBeanValidationApplication {
    /*
     * spring-boot-starter-web  -> spring-boot-starter-logging
     * 1. java.util.Logging
     * 2. Log4j2
     * 3. Logback (default)
     *
     * */
    public static void main(String[] args) {
        SpringApplication.run(LoggingExceptionHandlingBeanValidationApplication.class, args);
    }

}
