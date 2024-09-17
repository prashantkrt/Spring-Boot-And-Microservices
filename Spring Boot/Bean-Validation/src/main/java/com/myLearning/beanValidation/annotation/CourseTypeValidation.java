package com.myLearning.beanValidation.annotation;
/*
* Creating custom annotation is core java concept
* It's not related to Spring Boot
* */

import jakarta.validation.Constraint;

import java.lang.annotation.*;

// FIELD Level ,Method Level , Constructor Level and Parameter Level
// during runtime check
// @Documented to define the annotation
// Telling the java constraint which class will validate the actual logic for annotation

@Target({ElementType.FIELD, ElementType.METHOD,ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CourseTypeValidationImpl.class)
public @interface CourseTypeValidation {
    String message() default "Course Type Error";
}
