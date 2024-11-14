package com.mylearning.advice;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MetricAspectAdvice {

    private static final Logger log = LoggerFactory.getLogger(MetricAspectAdvice.class);

    // Actuators we use ObservationRegistry in Aspect
    @Autowired
    private ObservationRegistry registry;

    @After("execution (* com.mylearning.controller.ProductController.*(..))")
    public void sendMetrics(JoinPoint joinPoint) {
        log.info("Application collecting metrics for method: " + joinPoint.getSignature().getName());

        Observation.createNotStarted(joinPoint.getSignature().getName(), registry)
                .observe(() -> {
                    // Supplier FI
                    // Simulate some processing here
                    log.info("Observing arguments: {}", joinPoint.getArgs());
                });

        log.info("Application published the metrics for method: " + joinPoint.getSignature().getName());
    }


    public void performTask(JoinPoint joinPoint) {
        log.info("Inside the method performTask start-> {}", joinPoint.getSignature().getName());
        // Observing the execution of this task
        /*
         *  The .observe() method starts the observation, executes the task inside (logging, simulating processing time with Thread.sleep(500)),
         *  and stops the observation after the task completes.
         *
         * */
        Observation.createNotStarted("performTask.observation", registry)
                .observe(() -> {
                    // this will be the part of the metric
                    log.info("Starting task...");
                    try {
                        Thread.sleep(500); // Simulate some processing time
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    log.info("Task completed.");
                });
        log.info("Inside the method performTask end-> {}", joinPoint.getSignature().getName());
    }


    // some more example
    @After("execution (* com.mylearning.controller.ProductController.*(..))")
    public void sendMetrics2(JoinPoint joinPoint) {
        log.info("Application collecting metrics for method: " + joinPoint.getSignature().getName());

        // Observation only captures what's inside observe()
        Observation.createNotStarted(joinPoint.getSignature().getName(), registry)
                .observe(() -> {
                    // Code here is captured as a metric
                    log.info("Observing arguments: {}", joinPoint.getArgs()); // this will be the part of the metric
                    // Simulate some processing or important work
                    performMetricSpecificLogic(joinPoint);  // Example logic tracked in metric
                });

        log.info("Application published the metrics for method: " + joinPoint.getSignature().getName());
    }

    // this will be the part of the metric
    private void performMetricSpecificLogic(JoinPoint joinPoint) {
        // Simulated logic you want to measure
        try {
            Thread.sleep(100);  // Simulate some processing time, e.g., database call
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

