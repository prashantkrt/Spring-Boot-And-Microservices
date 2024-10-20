package com.mylearning.service;

import com.mylearning.entity.Employee;
import com.mylearning.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public String saveEmployee(Employee employee) {
        int count = employeeRepository.save(employee);
        return "RECORD INSERTED ! " + count;
    }

    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    public List<Employee> getEmployeesUsingBeanPropertyRowMapper(){
        return employeeRepository.getAllEmployees();
    }

    public Employee getEmployeeById(int id){
        return employeeRepository.findById(id);
    }


    public String getNameById(int id){
        return employeeRepository.getNameById(id);
    }

    public List<Employee> findEmployeesByNameAndDept(String name,String dept){
        return employeeRepository.findAllByNameAndDepartment(name, dept);
    }


    public String updateEmployee(Employee employee){
        int count= employeeRepository.updateEmployee(employee);
        return count+" RECORD UPDATED !";
    }

    public String deleteEmployee(int id){
        int count= employeeRepository.delete(id);
        return count+" RECORD DELETED !";
    }

}
