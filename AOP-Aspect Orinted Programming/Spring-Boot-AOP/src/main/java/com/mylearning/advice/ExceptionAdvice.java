package com.mylearning.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ExceptionAdvice {

    @Pointcut("execution (* com.mylearning.*.*.*(..))")
    public void alertFor() {
    }

    @AfterThrowing(value = "alertFor()", throwing = "exception") // Exception same name which we are using in param
    public void captureErrorAndSetAlerts(JoinPoint joinPoint, Exception exception) {
        log.error("Exception occurs in {}", joinPoint.getSignature());
        log.error("Exception message  {}", exception.getMessage());

        if (exception instanceof IllegalArgumentException) {
            //set the alerts
            //trigger an email to DEV team
            System.out.println("IllegalArgumentException occurs");
        }
        if (exception instanceof RuntimeException) {
            //set the alerts
            //trigger an email to DEV team
            System.out.println("RuntimeException occurs");
        }
    }
}
