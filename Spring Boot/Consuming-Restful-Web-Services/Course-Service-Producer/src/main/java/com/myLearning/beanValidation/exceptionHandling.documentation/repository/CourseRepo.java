package com.myLearning.beanValidation.exceptionHandling.documentation.repository;


import com.myLearning.beanValidation.exceptionHandling.documentation.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course, Integer> {
}
