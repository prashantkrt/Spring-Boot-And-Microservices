package com.mylearning.crud.crudh2.service;


import com.mylearning.crud.crudh2.dao.EmployeeDao;
import com.mylearning.crud.crudh2.dto.EmployeeDto;
import com.mylearning.crud.crudh2.entity.Employee;
import com.mylearning.crud.crudh2.util.EmployeeMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public Employee addEmployee(EmployeeDto employee) {
        Employee newEmployee = EmployeeMapper.getEmployee(employee);
        return employeeDao.save(newEmployee);
    }

    public Iterable<Employee> getAllEmployees() {
       return employeeDao.findAll();
    }

    public Employee getEmployeeById(int id) throws Exception {
        return employeeDao.findById((long)id).orElseThrow(()->new Exception("Error"));
    }

    public Employee updateEmployee(int id , EmployeeDto employeeDto) {
        Employee employee =  employeeDao.findById((long)id).get();
        return employeeDao.save(employee);
    }

    public String deleteEmployeeById(int id){
        employeeDao.deleteById((long)id);
        return "deleted Successfully";
    }
}
