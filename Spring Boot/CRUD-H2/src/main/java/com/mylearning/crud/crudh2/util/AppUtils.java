package com.mylearning.crud.crudh2.util;


import com.mylearning.crud.crudh2.dto.EmployeeRequestDto;
import com.mylearning.crud.crudh2.dto.EmployeeResponseDto;
import com.mylearning.crud.crudh2.entity.Employee;

public class AppUtils {

    public static Employee getEmployee(EmployeeRequestDto employeeDto) {
        Employee employee = new Employee();
        employee.setDepartment(employeeDto.getDepartment());
        employee.setName(employeeDto.getName());
        employee.setSalary(employeeDto.getSalary());
        return employee;
    }

    public static EmployeeResponseDto getEmployeeResponseDto(Employee employee) {
        EmployeeResponseDto employeeDto = new EmployeeResponseDto();
        employeeDto.setDepartment(employee.getDepartment());
        employeeDto.setName(employee.getName());
        employeeDto.setSalary(employee.getSalary());
        return employeeDto;
    }
}
