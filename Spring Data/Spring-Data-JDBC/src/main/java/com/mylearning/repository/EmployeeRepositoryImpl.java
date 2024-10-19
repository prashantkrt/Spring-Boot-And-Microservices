package com.mylearning.repository;

import com.mylearning.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        return jdbcTemplate.query("SELECT * FROM EMPLOYEES_DATA",
                (rs, rowNum) ->
                        Employee.builder()
                                .id(rs.getInt("id"))
                                .name(rs.getString("name"))
                                .department(rs.getString("department"))
                                .email(rs.getString("email"))
                                .dateOfJoining(rs.getDate("dateOfJoining"))
                                .build());
    }

    @Override
    public List<Employee> getAllEmployees() {
        return jdbcTemplate.query("SELECT * FROM EMPLOYEES", new BeanPropertyRowMapper<>(Employee.class));
    }

    @Override
    public List<Employee> findAllEmployees() {
        String sql = "SELECT * FROM Employees";
        return jdbcTemplate.query(sql, new EmployeeRowMapper());
    }

    @Override
    public Employee findById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM EMPLOYEES WHERE id =? ",
                new BeanPropertyRowMapper<>(Employee.class), id);
    }


    public Employee getEmployeeById(Integer id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM EMPLOYEES WHERE id = ?",
                new RowMapper<Employee>() {
                    @Override
                    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return Employee.builder()
                                .id(rs.getInt("id"))
                                .name(rs.getString("name"))
                                .department(rs.getString("department"))
                                .email(rs.getString("email"))
                                .phone(rs.getString("phone"))
                                .dateOfJoining(rs.getDate("dateOfJoining")) // Assuming Date type in Employee class
                                .build();
                    }
                },
                id
        );
    }

    public Employee fetchEmployeeById(Integer id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM EMPLOYEES WHERE id = ?",
                (rs, rowNum) -> Employee.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .department(rs.getString("department"))
                        .email(rs.getString("email"))
                        .phone(rs.getString("phone"))
                        .dateOfJoining(rs.getDate("dateOfJoining")) // Adjust type if using LocalDate
                        .build(),
                id
        );
    }

    public Employee fetchEmpById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM EMPLOYEES WHERE id = ?", new EmployeeRowMapper(), id);
    }

    @Override
    public Integer updateEmployee(Employee employee) {
        return jdbcTemplate.update("UPDATE EMPLOYEES SET name =? , department = ? , email = ? , dateOfJoining = ?  WHERE id =?",
                employee.getName(), employee.getDepartment(), employee.getEmail(), employee.getDateOfJoining(), employee.getId());
    }

    @Override
    public String getNameById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT name FROM EMPLOYEES WHERE id =? ",
                new BeanPropertyRowMapper<>(String.class), id);
    }

    @Override
    public Integer delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM EMPLOYEES_DATA WHERE id= ?", id);
    }

    @Override
    public Employee findByNameAndDepartment(String name, String department) {
        return jdbcTemplate.queryForObject("SELECT * FROM EMPLOYEES_DATA WHERE name = ? and department =? ",
                new BeanPropertyRowMapper<>(Employee.class), name, department);
    }

    @Override
    public List<Employee> findAllByNameAndDepartment(String name, String department) {
        return jdbcTemplate.query("SELECT * FROM EMPLOYEES_DATA WHERE name = ? and department =? ",
                new BeanPropertyRowMapper<>(Employee.class), name, department);
    }
}
