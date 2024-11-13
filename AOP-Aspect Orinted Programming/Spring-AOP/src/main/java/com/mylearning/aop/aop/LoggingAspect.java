package com.mylearning.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect // class where we have secondary logic is Aspect
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // return Type, class name, method name, args
    // PointCut -> (* *.*.(..))  all return type , all classes, all methods and all args

    @Before("execution (* com.mylearning.aop.service.JobService.getJob(..)) || execution (* com.mylearning.aop.service.JobService.getAllJobs(..))")
    public void logMethodCall(JoinPoint joinpoint) {

        logger.info("Executing method call before execution of getJob(..) method");
        //JoinPoint to get the hold on !!!


        logger.info(joinpoint.getSignature().getName()); //getJob
        // com.mylearning.aop.service.JobService + getJob
        logger.info(joinpoint.getSignature().getDeclaringTypeName() + "." + joinpoint.getSignature().getName());
    }

    @Before("execution(* com.mylearning.aop.service.JobService.getAllJobs(..))")
    //(..) in a pointcut expression means with or without parameters.
    //@Before("execution(* com.example.service.JobService.getAllJobs())") also you can do so for more specififc => both will work
    public void logBeforeGetAllJobs() {
        System.out.println("Fetching all job posts...");
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

/*
*  Terminology for Aspect Orin ted Programming
*
*  1. Aspect ⇒ A module that contains cross-cutting concerns (e.g., logging, security).
*  2. JointPoint ⇒ the point where this method is called is a JoinPoint where advice can be applied. A method call is a typical JoinPoint
*  3. Advice ⇒ Before, after, and around are types of advice
*  4. PointCut ⇒ A Pointcut is a set of rules that defines where and when to apply the advice
*
*   // Define the pointcut expression
    @Pointcut("execution(* com.example.service.*.*(..))")
    public void serviceMethods() {
        // This is a placeholder for the pointcut
    }

    // Apply advice (e.g., Before advice) to the methods matching the pointcut
    @Before("serviceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Before method execution: " + joinPoint.getSignature());
    }

    // Apply After advice to the same pointcut
    @After("serviceMethods()")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("After method execution: " + joinPoint.getSignature());
    }

   // Directly writing the pointcut expression inside the advice annotation
    @Before("execution(* com.example.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Before method execution: " + joinPoint.getSignature());
    }

    @After("execution(* com.example.service.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
         System.out.println("After method execution: " + joinPoint.getSignature());
    }

*
*
*
*  5. Target Object => The object whose methods are being advised by AOP. This is the actual object that contains the business logic (e.g., a service or repository class).
    @Service
     public class UserService {
      public void createUser(String username) {
        System.out.println("User created: " + username);
    }
   }
  Now, if you apply an aspect to log method execution, the UserService object becomes the target object.
   @Aspect
   @Component
    public class LoggingAspect {

        @Before("execution(* com.example.service.UserService.*(..))")
        public void logBefore(JoinPoint joinPoint) {
            System.out.println("Before method execution: " + joinPoint.getSignature());
        }
    }

*  6. Weaving =>Weaving is the process of applying aspects to target objects. Weaving happens at runtime, using proxies to apply the advice dynamically to method executions.
*  Spring weaves the aspect at runtime by creating a proxy for the target object.
*  The proxy intercepts method calls to the target object and applies the advice (logging, security checks, etc.).
*
* */
