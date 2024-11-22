package com.mylearning.springbootbatchfaulttolerance.config;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import java.io.FileNotFoundException;

public class MySkipPolicy implements SkipPolicy {

    @Override
    public boolean shouldSkip(Throwable t, long skipCount) throws SkipLimitExceededException {

        if(t instanceof FileNotFoundException){
            System.out.println("File not found Exception");
            return false;
        }
        if (t instanceof NumberFormatException) {
            System.out.println("Skipping item due to NumberFormatException");
            return true;
        }
        if(t instanceof NullPointerException){
            System.out.println("Skipping item due to NullPointerException");
            t.printStackTrace();  // Log the stack trace for debugging
            return true;
        }
        if (t instanceof ObjectOptimisticLockingFailureException) {
            System.out.println("Skipping item due to ObjectOptimisticLockingFailureException");
            return true; // Skip the item on version conflict
        }
        // Skip on any other exception if within the skip limit
        if (skipCount <= 10) {
            System.out.println("Skipping item due to exception: " + t.getClass().getSimpleName());
//            throw new SkipLimitExceededException("Skip limit exceeded. Aborting batch job.");        }
            return true;
        }
        System.out.println("Skip limit exceeded!");

        return false; // Do not skip if the skip limit is exceeded
    }
}
