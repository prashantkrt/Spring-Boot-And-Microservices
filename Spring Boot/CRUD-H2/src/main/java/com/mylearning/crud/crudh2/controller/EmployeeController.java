package com.mylearning.crud.crudh2.controller;

import com.mylearning.crud.crudh2.dto.EmployeeRequestDto;
import com.mylearning.crud.crudh2.dto.EmployeeResponseDto;
import com.mylearning.crud.crudh2.dto.ServiceResponse;
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
    public ServiceResponse<EmployeeResponseDto> addEmployee(@RequestBody EmployeeRequestDto employeeDto) {
        //return new ServiceResponse<>(HttpStatus.CREATED,employeeService.addEmployee(employeeDto)); // 201
        ServiceResponse<EmployeeResponseDto> serviceResponse = new ServiceResponse<>();
        try{
            EmployeeResponseDto employeeResponseDto = employeeService.addEmployee(employeeDto);
            serviceResponse.setResponse(employeeResponseDto);
            serviceResponse.setStatus(HttpStatus.CREATED);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            serviceResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return serviceResponse;
    }

    @GetMapping("/getEmployee")
    public ServiceResponse<EmployeeResponseDto>  getEmployeeById(@RequestParam int id) throws Exception {
        return new ServiceResponse<>(HttpStatus.OK,employeeService.getEmployeeById(id));
    }


    @PutMapping("updateEmployee/{empId}")
    public ResponseEntity<?> updateEmployee(@PathVariable("empId") int id, EmployeeRequestDto employeeDto) {
        return new ResponseEntity<>(employeeService.updateEmployee(id, employeeDto), HttpStatus.OK);
    }

    @DeleteMapping("deleteEmployee")
    public ResponseEntity<?> deleteEmployee(@RequestParam("empId") int id) {
        return new ResponseEntity<>(employeeService.deleteEmployeeById(id), HttpStatus.OK);
    }
}
