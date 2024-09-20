package com.mylearning.webservices.restfulwebservices.repository;

import com.mylearning.webservices.restfulwebservices.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//layer 3 Database layer
@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {
}
