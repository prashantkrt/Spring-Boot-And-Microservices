package com.myLearning.beanValidation.exceptionHandling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoggingExceptionHandlingBeanValidationApplication {
    /*
     * spring-boot-starter-web  → spring-boot-starter-logging → spring-jcl (spring commons logging) → Logback(has impl classes ⇒ Logback core and Logback classic(sl4j api))
     * 1. java.util.Logging
     * 2. Log4j2
     * 3. Logback (default to spring boot) because of the Logback classic which has sl4j
     * and sl4j is the abstraction of all the logging framework
     *
     * */
    public static void main(String[] args) {
        SpringApplication.run(LoggingExceptionHandlingBeanValidationApplication.class, args);
    }

    /* Basic steps which Logging frameworks follows
     * Logger ⇒ captures the event message
     * Formatter ⇒ Formats the message captured by logger
     * Handler ⇒ Dispatch the message by printing them in console or store them in a file
     * */

}
