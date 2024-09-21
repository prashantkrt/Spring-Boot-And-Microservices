package com.mylearning.actuator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mylearning.actuator.dto.Employee;
import com.mylearning.actuator.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() throws JsonProcessingException {
        List<Employee> employees = employeeService.getEmployees();
        log.info("EmployeeController:getAllEmployees fetch all employees {}", new ObjectMapper().writeValueAsString(employees));
        return employees;
    }



}
