package com.myLearning.beanValidation.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class CourseTypeValidationImpl implements ConstraintValidator<CourseTypeValidation,String> {
    @Override
    public boolean isValid(String courseType, ConstraintValidatorContext context) {
        List<String> list= Arrays.asList("Live","Recording");
        return list.contains(courseType);
    }
}
