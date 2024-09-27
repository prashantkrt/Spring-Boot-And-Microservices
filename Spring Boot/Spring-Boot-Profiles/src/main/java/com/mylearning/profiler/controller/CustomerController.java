package com.mylearning.profiler.controller;

import com.mylearning.profiler.dto.CustomerDTO;
import com.mylearning.profiler.model.Customer;
import com.mylearning.profiler.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping("/save")
    public List<Customer> saveCustomers(@RequestBody List<Customer> customers) {
        return service.addNewCustomers(customers);
    }

    @GetMapping("/get")
    public List<CustomerDTO> fetchAllCustomers() {
        return service.getCustomers();
    }
}
