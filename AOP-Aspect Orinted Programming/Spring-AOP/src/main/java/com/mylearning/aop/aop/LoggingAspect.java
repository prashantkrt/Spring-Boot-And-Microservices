package com.mylearning.aop.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    public void logMethodCall() {
        System.out.println("Logging MethodCall");
    }
}
