package com.mylearning.crud.crudh2.controller;


import com.mylearning.crud.crudh2.dto.EmployeeDto;
import com.mylearning.crud.crudh2.entity.Employee;
import com.mylearning.crud.crudh2.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class EmployeeController {

   @Autowired
   private EmployeeService employeeService;

   @PostMapping("addEmployee")
   public ResponseEntity<Employee> addEmployee(EmployeeDto employeeDto) {
    Employee employee =  employeeService.addEmployee(employeeDto);
    return ResponseEntity.ok(employee);
   }


}
