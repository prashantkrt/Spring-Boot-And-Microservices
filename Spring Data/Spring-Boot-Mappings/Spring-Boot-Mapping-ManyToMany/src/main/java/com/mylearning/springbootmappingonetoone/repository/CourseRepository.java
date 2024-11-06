package com.mylearning.springbootmappingonetoone.repository;

import com.mylearning.springbootmappingonetoone.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
