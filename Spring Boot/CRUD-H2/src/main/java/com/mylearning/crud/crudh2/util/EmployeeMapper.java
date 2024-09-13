package com.mylearning.crud.crudh2.util;

import com.mylearning.crud.crudh2.dto.EmployeeDto;
import com.mylearning.crud.crudh2.entity.Employee;

public class EmployeeMapper {

    public static Employee getEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setDepartment(employeeDto.getDepartment());
        employee.setName(employeeDto.getName());
        employee.setSalary(employeeDto.getSalary());
        return employee;
    }

    public static EmployeeDto getEmployeeDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setDepartment(employee.getDepartment());
        employeeDto.setName(employee.getName());
        employeeDto.setSalary(employee.getSalary());
        return employeeDto;
    }
}
