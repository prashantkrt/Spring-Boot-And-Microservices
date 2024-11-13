package com.mylearning.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
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
    public void logRequest(JoinPoint joinPoint) throws JsonProcessingException {
        log.info("class name {} ,method name {} ", joinPoint.getTarget(), joinPoint.getSignature().getName());
        log.info("Request Body {} ", new ObjectMapper().writeValueAsString(joinPoint.getArgs()));
    }


}
