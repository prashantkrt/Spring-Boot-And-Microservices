package com.mylearning.springbootmappingmanytmany.repository;

import com.mylearning.springbootmappingmanytmany.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
