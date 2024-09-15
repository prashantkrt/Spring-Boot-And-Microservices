package com.myLearning.beanValidation.repository;

import com.myLearning.beanValidation.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course, Integer> {
}
