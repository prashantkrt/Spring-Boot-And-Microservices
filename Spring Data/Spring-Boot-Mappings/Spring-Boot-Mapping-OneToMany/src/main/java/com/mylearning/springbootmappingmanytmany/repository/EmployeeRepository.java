package com.mylearning.springbootmappingmanytmany.repository;

import com.mylearning.springbootmappingmanytmany.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
