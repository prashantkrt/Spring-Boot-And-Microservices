package com.mylearning.springbootmappingonetoone;

import com.mylearning.springbootmappingonetoone.entity.Employee;
import com.mylearning.springbootmappingonetoone.entity.Address;
import com.mylearning.springbootmappingonetoone.repository.EmployeeRepository;
import com.mylearning.springbootmappingonetoone.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootMappingOneToManyApplication implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;
    private final AddressRepository addressRepository;

    public SpringBootMappingOneToManyApplication(EmployeeRepository employeeRepository, AddressRepository addressRepository) {
        this.employeeRepository = employeeRepository;
        this.addressRepository = addressRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMappingOneToManyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Employee employee = new Employee();
        employee.setEmployeeId(1);
        employee.setEmployeeName("Prasanna");
        employee.setEmployeeDepartment("IT");
        employee.setSalary(1234.00);



        Address address = new Address();
        address.setAddressId(101);
        address.setCity("San Francisco");
        address.setState("CA");
        address.setCountry("USA");
        address.setZip("123456");

        fetchDetails();
    }


    public void fetchDetails() {
        //Bi-Directional

    }
}
