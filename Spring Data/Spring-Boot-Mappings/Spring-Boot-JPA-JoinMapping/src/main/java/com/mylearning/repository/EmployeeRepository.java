package com.mylearning.repository;

import com.mylearning.entity.Engineer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Engineer, Long> {
}
