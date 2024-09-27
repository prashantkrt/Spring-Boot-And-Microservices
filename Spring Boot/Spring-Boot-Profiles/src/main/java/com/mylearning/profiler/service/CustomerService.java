package com.mylearning.profiler.service;

import com.mylearning.profiler.dto.CustomerDTO;
import com.mylearning.profiler.model.Customer;
import com.mylearning.profiler.repository.CustomerRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
//@Profile(value = {"dev", "stg", "prod"})
@Profile(value = {"stg", "prod"}) // won't work as property is set to dev env
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Value("${application.message}")
    private String message;

    @Value("${spring.datasource.username}")
    private String currentUser;

    @PostConstruct
    public void init() {
        System.out.println("************* " + message);
        System.out.println("************* " + currentUser);
    }

    public List<Customer> addNewCustomers(List<Customer> customers) {
        return repository.saveAll(customers);
    }

    @PostConstruct
    public List<Customer> addNewCustomerPreLoad() {
        List<Customer> costomerList = IntStream.rangeClosed(1, 10).mapToObj(i -> {
            return new Customer("Customer" + i, "customerEmail" + i + "@gmail.com", generateRandomMobileNumber(), getDateFormat(new Date()));
        }).toList();
        return repository.saveAll(costomerList);
    }

    private String generateRandomMobileNumber() {

        Random random = new Random();

        // The first digit of a mobile number can be from 7 to 9 for Indian numbers
        int firstDigit = random.nextInt(3) + 7; // Generates 7, 8, or 9
        StringBuilder mobileNumber = new StringBuilder(String.valueOf(firstDigit));

        // Generate the remaining 9 digits
        for (int i = 1; i < 10; i++) {
            int digit = random.nextInt(10); // Generates 0 to 9
            mobileNumber.append(digit);
        }

        return mobileNumber.toString();
    }

    public List<CustomerDTO> getCustomers() {
        List<Customer> customerList = repository.findAll();
        return customerList.stream().map(customer ->
                        new CustomerDTO(customer.getId(), customer.getName(), customer.getEmail(), customer.getPhone(),
                                getDateFormat(customer.getBirthDate())))
                .collect(Collectors.toList());
    }

    private String getDateFormat(Date date) {
        return new SimpleDateFormat("MM/dd/yyyy").format(date);
    }
}
