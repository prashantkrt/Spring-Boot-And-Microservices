package com.myLearning.controller;

import com.myLearning.entity.Employee;
import com.myLearning.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    /*
     *  Authorization:
     *  /create ⇒ HR
     *  /all ⇒ Manager and HR
     *  /id ⇒ Employee
     * */

    @Autowired
    private EmployeeService service;

    //publicly accessible
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Employee Management System";
    }

    //only for HR
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_HR')")
    public Employee onboardNewEmployee(@RequestBody Employee employee) {
        return service.createEmployee(employee);
    }

    //For HR and Manager
    @PreAuthorize("hasAuthority('ROLE_HR') or hasAnyAuthority('ROLE_MANAGER')")
    @GetMapping("/all")
    public List<Employee> getAll() {
        return service.getAllEmployees();
    }

    //For Employee
    @PreAuthorize("hasAuthority('ROLE_USER')")
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
