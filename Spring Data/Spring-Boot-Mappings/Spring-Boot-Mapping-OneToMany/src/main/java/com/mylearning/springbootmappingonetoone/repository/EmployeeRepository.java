package com.mylearning.springbootmappingonetoone.repository;

import com.mylearning.springbootmappingonetoone.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
