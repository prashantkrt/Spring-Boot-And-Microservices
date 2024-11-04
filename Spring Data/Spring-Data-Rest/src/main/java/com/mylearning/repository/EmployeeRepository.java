package com.mylearning.repository;

import com.mylearning.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findByName(String name);
    //http://localhost:8080/student-api/search/findByName?name="Prashant"
}
