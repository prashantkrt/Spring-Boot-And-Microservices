package com.myLearning.beanValidation.exceptionHandling.exception;

// unchecked Exception
// if you want it to be checked and mandatory to handle it then extend it from Exception

public class CourseServiceBusinessException extends RuntimeException {
    public CourseServiceBusinessException(String message) {
        super(message);
    }
}
