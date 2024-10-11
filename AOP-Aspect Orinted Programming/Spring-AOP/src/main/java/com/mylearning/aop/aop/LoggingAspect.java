package com.mylearning.aop.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution (* com.mylearning.aop.service.JobService.getJob(..))")
    public void logMethodCall() {
       logger.info("Executing method call before execution of getJob(..) method");
    }
}
