package com.mylearning.crud.crudh2.service;

import com.mylearning.crud.crudh2.dao.EmployeeDao;
import com.mylearning.crud.crudh2.dto.EmployeeRequestDto;
import com.mylearning.crud.crudh2.dto.EmployeeResponseDto;
import com.mylearning.crud.crudh2.entity.Employee;
import com.mylearning.crud.crudh2.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public EmployeeResponseDto addEmployee(EmployeeRequestDto employeeRequestDto) {
        Employee newEmployee = employeeDao.save(AppUtils.getEmployee(employeeRequestDto));
        EmployeeResponseDto employeeResponseDto = AppUtils.getEmployeeResponseDto(newEmployee);
        employeeResponseDto.setEmployeeUniqueCode(UUID.randomUUID().toString().split("-")[0]);
        return employeeResponseDto;
    }

    public List<EmployeeResponseDto> getAllEmployees() {
        Iterable<Employee> employees = employeeDao.findAll();
//        List<EmployeeResponseDto> list = StreamSupport.stream(employees.spliterator(), false)
//                .map(AppUtils::getEmployeeResponseDto)
//                .collect(Collectors.toList());
//        list.forEach(i -> i.setEmployeeUniqueCode(UUID.randomUUID().toString().split("-")[0]));
//        return list;

        // better
        return StreamSupport.stream(employees.spliterator(), false)
                .map(employee -> {
                    EmployeeResponseDto dto = AppUtils.getEmployeeResponseDto(employee);
                    dto.setEmployeeUniqueCode(UUID.randomUUID().toString().split("-")[0]);
                    return dto;
                }).collect(Collectors.toList());
    }

    public List<EmployeeResponseDto> getAllEmployeeById(Iterable<Long> id) {
        Iterable<Employee> employeeItr = employeeDao.findAllById(id);
        return StreamSupport.stream(employeeItr.spliterator(), false)
                .map(i -> {
                    EmployeeResponseDto dto = AppUtils.getEmployeeResponseDto(i);
                    dto.setEmployeeUniqueCode(UUID.randomUUID().toString().split("-")[0]);
                    return dto;
                }).collect(Collectors.toList());
    }

    public EmployeeResponseDto getEmployeeById(int id) {
        Employee employee = employeeDao.findById((long) id).orElseThrow(() -> new RuntimeException("Error"));
        EmployeeResponseDto dto = AppUtils.getEmployeeResponseDto(employee);
        dto.setEmployeeUniqueCode(UUID.randomUUID().toString().split("-")[0]);
        return dto;
    }

    public EmployeeResponseDto updateEmployee(int id, EmployeeRequestDto employeeDto) {
        Employee existingEmployee = employeeDao.findById((long) id).orElseThrow(() -> new RuntimeException("Error"));
        existingEmployee.setDepartment(employeeDto.getDepartment());
        existingEmployee.setName(employeeDto.getName());
        existingEmployee.setSalary(employeeDto.getSalary());
        Employee updatedEmployee = employeeDao.save(existingEmployee);
        EmployeeResponseDto employeeResponseDto = AppUtils.getEmployeeResponseDto(updatedEmployee);
        employeeResponseDto.setEmployeeUniqueCode(UUID.randomUUID().toString().split("-")[0]);
        return employeeResponseDto;
    }

    public String deleteEmployeeById(int id) {
        employeeDao.deleteById((long) id);
        return "deleted Successfully";
    }
}
