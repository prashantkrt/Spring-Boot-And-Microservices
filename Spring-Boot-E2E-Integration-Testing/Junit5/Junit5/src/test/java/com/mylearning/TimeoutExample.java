package com.mylearning;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

public class TimeoutExample {
    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    void testTimeout() throws InterruptedException {
        // Simulating a time-consuming task
        Thread.sleep(500);  // This will pass
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    void testTimeoutFailure() throws InterruptedException {
        // Simulating a time-consuming task that exceeds timeout
        Thread.sleep(2000);  // This will fail
    }
}
