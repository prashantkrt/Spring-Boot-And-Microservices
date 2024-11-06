package com.mylearning.springbootmappingonetoone;

import com.mylearning.springbootmappingonetoone.entity.Employee;
import com.mylearning.springbootmappingonetoone.entity.Address;
import com.mylearning.springbootmappingonetoone.experiment.Category;
import com.mylearning.springbootmappingonetoone.experimentRepository.CategoryRepo;
import com.mylearning.springbootmappingonetoone.experimentRepository.ItemRepo;
import com.mylearning.springbootmappingonetoone.repository.EmployeeRepository;
import com.mylearning.springbootmappingonetoone.repository.AddressRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringBootMappingOneToManyApplication implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;
    private final AddressRepository addressRepository;
    private final CategoryRepo categoryRepo;
    private final ItemRepo itemRepo;

    public SpringBootMappingOneToManyApplication(EmployeeRepository employeeRepository,AddressRepository addressRepository,CategoryRepo categoryRepo,ItemRepo itemRepo) {
        this.employeeRepository = employeeRepository;
        this.addressRepository = addressRepository;
        this.categoryRepo = categoryRepo;
        this.itemRepo = itemRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMappingOneToManyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Employee employee = new Employee();
        employee.setEmployeeId(1);
        employee.setEmployeeName("Harry Potter");
        employee.setEmployeeDepartment("IT");
        employee.setSalary(1234.00);

        Address address1 = new Address();
        address1.setAddressId(1);
        address1.setStreet("23 road 11");
        address1.setCity("San Francisco");
        address1.setState("CA");
        address1.setCountry("USA");

        Address address2 = new Address();
        address2.setAddressId(2);
        address2.setStreet("23 road 12");
        address2.setCity("San Francisco");
        address2.setState("CA");
        address2.setCountry("USA");

        employee.setAddressList(List.of(address1, address2));

        addressRepository.save(address1); // only for address
        addressRepository.save(address2); // only for address

        Address address = new Address();
        address.setAddressId(101);
        address.setCity("San Francisco");
        address.setState("CA");
        address.setStreet("123 Main St");
        address.setCountry("USA");
        address.setZip("123456");

        address.setEmployee(employee);
        employee.setAddressList(List.of(address));

        employeeRepository.save(employee); // we update both the tables


        Employee employee2 = new Employee();
        employee.setEmployeeId(2);
        employee.setEmployeeName("Harry Potter");
        employee.setEmployeeDepartment("IT");
        employee.setSalary(1234.00);

        Address address3 = new Address();
        address3.setAddressId(15);
        address3.setStreet("23 road 11");
        address3.setCity("San Francisco");
        address3.setState("CA");
        address3.setCountry("USA");
        employee2.setAddressList(List.of(address3));

        addressRepository.save(address3); // haven't updated both the tables, only updated address table

        fetchDetails();
    }



    public void fetchDetails() {
        //Bi-Directional
        Category category = categoryRepo.findById(10002).get();
        System.out.println(category.getItems());
    }
}
