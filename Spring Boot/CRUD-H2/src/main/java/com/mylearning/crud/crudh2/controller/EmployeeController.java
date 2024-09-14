package com.mylearning.crud.crudh2.controller;

import com.mylearning.crud.crudh2.dto.EmployeeRequestDto;
import com.mylearning.crud.crudh2.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("addEmployee")
    public ResponseEntity<?> addEmployee(@RequestBody EmployeeRequestDto employeeDto) {
        return new ResponseEntity<>(employeeService.addEmployee(employeeDto), HttpStatus.CREATED); // 201
    }

    @GetMapping("/getEmployee")
    public ResponseEntity<?> getEmployeeById(@RequestParam int id) throws Exception {
        return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }

    @PutMapping("updateEmployee")
    public ResponseEntity<?> updateEmployee(@RequestParam int id, EmployeeRequestDto employeeDto) {
        return new ResponseEntity<>(employeeService.updateEmployee(id, employeeDto), HttpStatus.OK);
    }

    @DeleteMapping("deleteEmployee")
    public ResponseEntity<?> deleteEmployee(@RequestParam int id) {
        return new ResponseEntity<>(employeeService.deleteEmployeeById(id), HttpStatus.OK);
    }
}
