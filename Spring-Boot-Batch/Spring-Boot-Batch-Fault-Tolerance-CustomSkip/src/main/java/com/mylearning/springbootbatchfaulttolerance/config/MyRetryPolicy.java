package com.mylearning.springbootbatchfaulttolerance.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.context.RetryContextSupport;

import java.io.FileNotFoundException;

// way 1 for retry
public class MyRetryPolicy implements RetryPolicy {

    private final int maxRetries;  // Max number of retries
    private int retryCount;        // Current retry count


    public MyRetryPolicy(int maxRetries) {
        this.maxRetries = maxRetries;
        this.retryCount = 0;
    }

    @Override
    public boolean canRetry(RetryContext context) {
        return context.getRetryCount() < maxRetries;
    }

    @Override
    public RetryContext open(RetryContext retryContext) {
        // Initialize a new retry context
        return new RetryContextSupport(retryContext);
    }

    @Override
    public void close(RetryContext retryContext) {
        // Optionally, do any cleanup if necessary
        // For now, it's empty as cleanup isn't required
        // Optionally, log the result of retry attempts (success/failure)
        if (retryContext.getRetryCount() < maxRetries) {
            System.out.println("Retry successful after " + (retryContext.getRetryCount() + 1) + " attempts.");
        } else {
            System.out.println("Max retry attempts reached. Giving up.");
            System.out.println("Retry process completed.");
        }
    }

    @Override
    public void registerThrowable(RetryContext context, Throwable throwable) {
        retryCount++;
        // Log the exception and track the retry count
        if (context.getRetryCount() < maxRetries) {
            System.out.println("Retrying... attempt #" + (context.getRetryCount() + 1)); // is because context.getRetryCount() returns the number of retries that have already occurred, not the current retry attempt => for adding + 1
        }
        // Decide whether to retry based on an exception type
        else if (throwable instanceof NullPointerException || throwable instanceof FileNotFoundException) {
            // These exceptions should trigger a retry
            System.out.println("Retrying... attempt #" + retryCount + " due to: " + throwable.getClass().getSimpleName());
        } else if (throwable instanceof IllegalArgumentException) {
            // These exceptions should not trigger a retry
            System.out.println("Skipping retry due to: " + throwable.getClass().getSimpleName());
            throw new IllegalArgumentException("Permanent error: " + throwable.getMessage());
        } else {
            // Other exceptions may not trigger a retry, so handle accordingly
            System.out.println("Non-retryable exception: " + throwable.getClass().getSimpleName());
            throw new RuntimeException("Permanent error: " + throwable.getMessage());
        }
    }
}
