package com.myLearning.controller;

import com.myLearning.entity.Employee;
import com.myLearning.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Employee onboardNewEmployee(@RequestBody Employee employee) {
        return service.createEmployee(employee);
    }

    //For HR and Manager
    @GetMapping("/all")
    public List<Employee> getAll() {
        return service.getAllEmployees();
    }

    //For Employee
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) {
        return service.getEmployeeById(id);
    }

}
