package com.myLearning.controller;

import com.myLearning.dto.AuthenticationRequest;
import com.myLearning.entity.Employee;
import com.myLearning.service.EmployeeService;
import com.myLearning.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/authentication")
    public String authenticateRequest(@RequestBody AuthenticationRequest authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateJwtToken(authenticationRequest.getUserName());
        } else {
            throw new UsernameNotFoundException("Authentication failed !");
        }
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Employee Management System";
    }

    @PostMapping("/create")
    public Employee onboardNewEmployee(@RequestBody Employee employee) {
        return service.createEmployee(employee);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_HR') or hasAnyAuthority('ROLE_MANAGER')")
    public List<Employee> getAll() {
        return service.getAllEmployees();
    }

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
