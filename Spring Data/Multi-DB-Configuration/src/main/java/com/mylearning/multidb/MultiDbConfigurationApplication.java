package com.mylearning.multidb;

import com.mylearning.multidb.model.Customer;
import com.mylearning.multidb.model.Product;
import com.mylearning.multidb.repository.CustomerRepository;
import com.mylearning.multidb.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class MultiDbConfigurationApplication implements CommandLineRunner {

    private final ProductRepository productRepository;

    private final CustomerRepository customerRepository;

    public MultiDbConfigurationApplication(ProductRepository productRepository, CustomerRepository customerRepository) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(MultiDbConfigurationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        List<Product> products = Arrays.asList(new Product("Books", "ComputerScience"),
                new Product("Chair", "Office Chair"),
                new Product("Table", "Office Table")
        );

        List<Customer> customers = Arrays.asList(new Customer("John Doe", "Cape Town"),
                new Customer("Rakesh", "New Delhi")
        );


        productRepository.saveAll(products);
        customerRepository.saveAll(customers);
    }
}
