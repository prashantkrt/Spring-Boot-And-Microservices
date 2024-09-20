package com.myLearning.beanValidation.exceptionHandling.documentation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD,ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CourseTypeValidationImpl.class)
public @interface CourseTypeValidation {
    // all the below is required if you want to send the message field
    String message() default "Course Type Error";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
