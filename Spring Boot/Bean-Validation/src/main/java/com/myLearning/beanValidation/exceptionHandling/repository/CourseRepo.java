package com.myLearning.beanValidation.exceptionHandling.repository;

import com.myLearning.beanValidation.exceptionHandling.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course, Integer> {
}
