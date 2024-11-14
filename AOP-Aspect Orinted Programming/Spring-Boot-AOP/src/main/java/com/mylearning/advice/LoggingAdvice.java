package com.mylearning.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAdvice {

    // PointCut -> (* *.*.(..))  all return type , all classes, all methods and all args
    @Pointcut("execution(* com.mylearning.service.ProductService.saveOrUpdateProduct(..))")
    private void logPointcut() {
    }

    @Before("logPointcut()")
    public void logRequestForSave(JoinPoint joinPoint) throws JsonProcessingException {
        log.info("class name {} ,method name {} ", joinPoint.getTarget(), joinPoint.getSignature().getName());
        log.info("Request Body {} ", new ObjectMapper().writeValueAsString(joinPoint.getArgs()));
    }

    @Before(value = "execution (* com.mylearning.service.ProductService.*(..))")
    public void logRequest(JoinPoint joinPoint) throws JsonProcessingException {
        log.info("class name->{} ,method name->{} ", joinPoint.getTarget(), joinPoint.getSignature().getName());
        log.info("Request Body->{} ", new ObjectMapper().writeValueAsString(joinPoint.getArgs()));
    }

    @After(value = "execution (* com.mylearning.service.ProductService.*(..))")
    public void logResponse(JoinPoint joinPoint) throws JsonProcessingException {
        log.info("LoggingAdvice::logResponse class name {} ,method name {} ", joinPoint.getTarget(), joinPoint.getSignature().getName());
        log.info("LoggingAdvice::logResponse Response Body {} ", new ObjectMapper().writeValueAsString(joinPoint.getArgs()));
    }

    @AfterReturning(value = "execution (* com.mylearning.service.ProductService.*(..))")
    public void logResponseAfterReturn(JoinPoint joinPoint) throws JsonProcessingException {
        log.info("LoggingAdvice::logResponse class name {} ,method name {} after method completion", joinPoint.getTarget(), joinPoint.getSignature().getName());
        log.info("LoggingAdvice::logResponse Response Body {} after method completion ", new ObjectMapper().writeValueAsString(joinPoint.getArgs()));
    }
}
