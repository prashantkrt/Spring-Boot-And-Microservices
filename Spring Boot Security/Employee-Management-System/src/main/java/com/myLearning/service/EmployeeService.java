package com.myLearning.service;

import com.myLearning.entity.Employee;
import com.myLearning.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    public static final String DEFAULT_ROLE = "ROLE_EMPLOYEE";

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Employee createEmployee(Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.setRoles(DEFAULT_ROLE);
        return employeeRepository.save(employee);
//        Employee emp = employeeRepository.findByUsername("user1").get();
//        String rawPassword = "user";
//        boolean matches = passwordEncoder.matches(rawPassword, emp.getPassword());
//        System.out.println(matches+"->"+"Testing and validating");

    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    public Employee changeRoleOfEmployee(Employee employee) {
        Employee existingEmployee = getEmployeeById(employee.getId());
        existingEmployee.setRoles(employee.getRoles());
        return employeeRepository.save(existingEmployee);
    }
}
