package com.myLearning.repository;

import com.myLearning.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    Optional<Employee> findByUserName(String username);
}
