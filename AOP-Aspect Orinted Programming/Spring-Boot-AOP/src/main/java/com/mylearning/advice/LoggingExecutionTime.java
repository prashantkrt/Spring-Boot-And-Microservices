package com.mylearning.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingExecutionTime {
    
    // Will check for this annotation and will
    @Around("@annotation(com.mylearning.annotation.TrackMethodExecutionTime)")
    // @Around("execution (* com.mylearning.*.*.*(..))")
    public Object logExecutionDuration(ProceedingJoinPoint pjp) throws Throwable {
        //before advice
        long startTime = System.currentTimeMillis();
        Object obj = pjp.proceed();
        //after advice
        long endTime = System.currentTimeMillis();
        log.info(" method {} execution takes {} ms times to complete ", pjp.getSignature(), (endTime - startTime));
        return obj;
    }
}
