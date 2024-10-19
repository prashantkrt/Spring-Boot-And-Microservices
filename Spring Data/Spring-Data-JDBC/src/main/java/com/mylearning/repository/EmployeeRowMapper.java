package com.mylearning.repository;

import com.mylearning.entity.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Employee().builder()
                .name(rs.getString("name"))
                .id(rs.getInt("id"))
                .email(rs.getString("email"))
                .phone(rs.getString("phone"))
                .dateOfJoining(rs.getDate("dateOfJoining"))
                .department(rs.getString("department"))
                .build();
    }

}
