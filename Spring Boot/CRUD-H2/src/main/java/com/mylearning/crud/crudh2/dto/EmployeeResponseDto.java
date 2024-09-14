package com.mylearning.crud.crudh2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeResponseDto {
    private String employeeUniqueCode;
    private String name;
    private double salary;
    private String department;
}
