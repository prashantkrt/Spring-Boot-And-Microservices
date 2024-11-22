package com.mylearning.springbootbatchfaulttolerance.config;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;

import java.io.FileNotFoundException;

public class MySkipPolicy implements SkipPolicy {
    @Override
    public boolean shouldSkip(Throwable t, long skipCount) throws SkipLimitExceededException {

        if(t instanceof FileNotFoundException){
            System.out.println("File not found Exception");
            return false;
        }
        if(t instanceof NullPointerException){
            System.out.println("Skipping item due to NullPointerException");
            return true;
        }

        // Skip on any other exception if within skip limit
        if (skipCount <= 10000) {
            System.out.println("Skipping item due to exception: " + t.getClass().getSimpleName());
            return true;
        }


        if (skipCount > 10000) {
            System.out.println("Skip limit exceeded!");
            return false; // Do not skip if skip limit is exceeded
        }
        return false;
    }
}
