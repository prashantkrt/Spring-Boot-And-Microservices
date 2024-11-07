package com.mylearning.springbootmappingmanytmany.repository;

import com.mylearning.springbootmappingmanytmany.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
