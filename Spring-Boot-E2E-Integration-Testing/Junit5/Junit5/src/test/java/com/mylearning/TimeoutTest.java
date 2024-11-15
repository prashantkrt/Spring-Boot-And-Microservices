package com.mylearning;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeout;

public class TimeoutTest {
//    @FunctionalInterface
//    public interface Executable {
//        void execute() throws Throwable;
//    }

    // A sample method that simulates a task taking time
    public void timeConsumingTask() throws InterruptedException {
        // Simulating a long-running task
        Thread.sleep(3000);  // 1 second
    }

    @Test
    void testTimeoutUsingAssertTimeout() {
        // Duration timeout, Executable executable
        // Assert that the timeConsumingTask completes within 2 seconds
        assertTimeout(Duration.ofSeconds(2), () -> {
            timeConsumingTask();  // This method should complete within 2 seconds
        });
    }



    @Test
    void testTimeoutUsingAssertTimeout2() {
        //Duration timeout, Executable executable, String message
        assertTimeout(Duration.ofSeconds(2), () -> {
            timeConsumingTask();  // This should finish within 2 seconds
        }, "The method timeConsumingTask took longer than 2 seconds to execute!");
    }

    // A method simulating a long-running task
    public void task1() throws InterruptedException {
        Thread.sleep(1000);  // 1 second
    }

    // Another long-running task
    public void task2() throws InterruptedException {
        Thread.sleep(1200);  // 1.2 seconds
    }

    @Test
    void testMultipleTasksWithinTimeout() {
        assertTimeout(Duration.ofSeconds(3), () -> {
            task1();  // 1 second
            task2();  // 1.2 seconds
        }, "The tasks did not complete within 3 seconds!");
    }

    @Test
    @Timeout(3)  // Timeout after 3 seconds
    void testMethod() throws InterruptedException {
        // Simulate a task
        Thread.sleep(2000);  // This will complete in 2 seconds
    }

}