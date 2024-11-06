package com.mylearning.springbootmappingonetoone.repository;

import com.mylearning.springbootmappingonetoone.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
