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
    public Integer save(Employee employee) {
        String sql = "INSERT INTO Employees (name, department, email, phone, dateOfJoining) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, employee.getName(), employee.getDepartment(), employee.getEmail(), employee.getPhone(), employee.getDateOfJoining());
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
        return jdbcTemplate.update("UPDATE EMPLOYEES_DATA SET name =? , dept = ? , email = ? , doj = ?  WHERE id =?",
                employee.getName(), employee.getDepartment(), employee.getEmail(), employee.getDateOfJoining(), employee.getId());
    }

    @Override
    public String getNameById(Integer id) {
        return "";
    }

    @Override
    public Integer delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM EMPLOYEES_DATA WHERE id= ?", id);
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
