package com.mylearning.repository;

import com.mylearning.entity.Employee;

import java.util.List;

public interface EmployeeRepository {
    public String save(Employee employee);

    public List<Employee> findAll();

    public Employee findById(Integer id);

    public Integer updateEmployee(Employee employee);

    public String getNameById(Integer id);

    public Integer delete(Integer id);

    public Employee findByNameAndDepartment(String name, String department);

    public List<Employee> findByNameAndDepartment(String department);
}
