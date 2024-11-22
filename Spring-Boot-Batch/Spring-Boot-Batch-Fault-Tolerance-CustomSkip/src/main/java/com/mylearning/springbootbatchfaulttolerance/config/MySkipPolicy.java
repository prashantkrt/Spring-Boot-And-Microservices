package com.mylearning.springbootbatchfaulttolerance.config;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;

import java.io.FileNotFoundException;

public class MySkipPolicy implements SkipPolicy {
    @Override
    public boolean shouldSkip(Throwable t, long skipCount) throws SkipLimitExceededException {
        if(t instanceof FileNotFoundException){
            return false;
        }
        if(t instanceof NullPointerException){
            return true;
        }
        return false;
    }
}
