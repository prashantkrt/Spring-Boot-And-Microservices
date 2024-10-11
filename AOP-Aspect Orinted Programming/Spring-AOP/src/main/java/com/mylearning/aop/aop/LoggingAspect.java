package com.mylearning.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.annotation.After;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // return Type , class name , method name , args
    // PointCut -> (* *.*.(..))  all return type , all classes , all methods and all args

    @Before("execution (* com.mylearning.aop.service.JobService.getJob(..)) || execution (* com.mylearning.aop.service.JobService.getAllJobs(..))")
    public void logMethodCall(JoinPoint joinpoint) {

       logger.info("Executing method call before execution of getJob(..) method");
       //JoinPoint to get the hold on !!!


       logger.info(joinpoint.getSignature().getName()); //getJob
        // com.mylearning.aop.service.JobService + getJob
       logger.info(joinpoint.getSignature().getDeclaringTypeName() + "." + joinpoint.getSignature().getName());
    }

    // when we use After it act as After Finally it will execute even some issue comes
    @After("execution (* com.mylearning.aop.service.JobService.getJob(..)) || execution(* com.mylearning.aop.service.JobService.updateJob(..))")
    public void logMethodExecuted(JoinPoint jp) {
        logger.info("Method Executed "+jp.getSignature().getName());
    }

    @AfterThrowing("execution (* com.mylearning.aop.service.JobService.getJob(..))) || execution(* com.mylearning.aop.service.JobService.updateJob(..))")
    public void logMethodCrashed(JoinPoint jp) {
        logger.info("Method has some issues "+jp.getSignature().getName());
    }

    @AfterReturning("execution (* com.mylearning.aop.service.JobService.getJob(..))) || execution(* com.mylearning.aop.service.JobService.updateJob(..))")
    public void logMethodExecutedSuccess(JoinPoint jp) {
        logger.info("Method Executed Successfully "+jp.getSignature().getName());
    }

}
