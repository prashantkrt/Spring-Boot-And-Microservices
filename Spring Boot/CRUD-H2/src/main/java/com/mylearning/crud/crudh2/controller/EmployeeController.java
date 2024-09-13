package com.mylearning.crud.crudh2.controller;


import com.mylearning.crud.crudh2.dto.EmployeeDto;
import com.mylearning.crud.crudh2.entity.Employee;
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
    public ResponseEntity<Employee> addEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeService.addEmployee(employeeDto);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @GetMapping("/getEmployee")
    public ResponseEntity<Employee> getEmployeeById(@RequestParam int id) throws Exception {
        return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }

    @PutMapping("updateEmployee")
    public ResponseEntity<Employee> updateEmployee(@RequestParam int id, EmployeeDto employeeDto) {
        return new ResponseEntity<>(employeeService.updateEmployee(id, employeeDto), HttpStatus.OK);
    }


    @DeleteMapping("deleteEmployee")
    public ResponseEntity<String> deleteEmployee(@RequestParam int id) {
        return new ResponseEntity<>(employeeService.deleteEmployeeById(id), HttpStatus.OK);
    }
}
