package com.mylearning.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
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
        logger.info("Method Executed " + jp.getSignature().getName());
    }

    @AfterThrowing("execution (* com.mylearning.aop.service.JobService.getJob(..))) || execution(* com.mylearning.aop.service.JobService.updateJob(..))")
    public void logMethodCrashed(JoinPoint jp) {
        logger.info("Method has some issues " + jp.getSignature().getName());
    }

    @AfterReturning("execution (* com.mylearning.aop.service.JobService.getJob(..))) || execution(* com.mylearning.aop.service.JobService.updateJob(..))")
    public void logMethodExecutedSuccess(JoinPoint jp) {
        logger.info("Method Executed Successfully " + jp.getSignature().getName());
    }

    //Around =>  start and end both the end
    //when we use Around we should be return the object else method won't work
    @Around("execution (* com.mylearning.aop.service.JobService.getJob(..))")
    public Object timeToExecute(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object object = joinPoint.proceed();// now start the method
        long endTime = System.currentTimeMillis();
        logger.info("Time Taken ->" + (endTime - startTime) + " ms");
        return object;
    }

    // Validating
    // Suppose if user sends -ve id value
    // through Around we can handle and pass the +ve value
    @Around("execution (* com.mylearning.aop.service.JobService.deleteJob(..)) && args(postId)")
    public Object validation(ProceedingJoinPoint jp,int postId) throws Throwable {
        if (postId<0) {
            logger.info("PostId is negative, updating it");
            postId=-postId;
            logger.info("new Value "+postId);
        }
        Object obj=jp.proceed(new Object[] {postId});
        return obj;
    }
}
