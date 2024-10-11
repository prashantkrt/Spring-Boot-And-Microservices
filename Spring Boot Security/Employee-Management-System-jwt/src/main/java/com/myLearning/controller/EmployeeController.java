package com.myLearning.controller;

import com.myLearning.dto.AuthenticationRequest;
import com.myLearning.entity.Employee;
import com.myLearning.service.EmployeeService;
import com.myLearning.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/authentication")
    public String authenticateRequest(@RequestBody AuthenticationRequest authenticationRequest) {
        return jwtService.generateJwtToken(authenticationRequest.getUserName());
    }

    //publicly accessible
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Employee Management System";
    }


    @PostMapping("/create")
    //@PreAuthorize("hasAuthority('ROLE_HR')")
    public Employee onboardNewEmployee(@RequestBody Employee employee) {
        return service.createEmployee(employee);
    }

    //For HR and Manager
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_HR') or hasAnyAuthority('ROLE_MANAGER')")
    public List<Employee> getAll() {
        return service.getAllEmployees();
    }

    //For Employee
    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) {
        return service.getEmployeeById(id);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_HR')")
    public Employee updateRoles(@RequestBody Employee employee) {
        return service.changeRoleOfEmployee(employee);
    }

}
