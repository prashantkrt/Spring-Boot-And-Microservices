package com.mylearning.repository;

import com.mylearning.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String save(Employee employee) {
        return "";
    }

    @Override
    public List<Employee> findAll() {
        return List.of();
    }

    @Override
    public List<Employee> getAllEmployees() {
        return List.of();
    }

    @Override
    public Employee findById(Integer id) {
        return null;
    }

    @Override
    public Integer updateEmployee(Employee employee) {
        return 0;
    }

    @Override
    public String getNameById(Integer id) {
        return "";
    }

    @Override
    public Integer delete(Integer id) {
        return 0;
    }

    @Override
    public Employee findByNameAndDepartment(String name, String department) {
        return null;
    }

    @Override
    public List<Employee> findByNameAndDepartment(String department) {
        return List.of();
    }
}
