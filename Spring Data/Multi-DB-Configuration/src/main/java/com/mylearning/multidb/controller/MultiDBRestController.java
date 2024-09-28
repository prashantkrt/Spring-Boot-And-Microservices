package com.mylearning.multidb.controller;

import com.mylearning.multidb.model.Customer;
import com.mylearning.multidb.model.Product;
import com.mylearning.multidb.service.CustomerService;
import com.mylearning.multidb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MultiDBRestController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    /*
     *  Just for learning ==> Postman headers section details
     *  =============================
     *  HTTP/1.1 200 OK
     *  Content-Type: application/json
     *  Cache-Control: no-cache
     *  Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6...
     *  Set-Cookie: sessionId=abc123; Path=/; HttpOnly
     *  X-Custom-Header: Custom-Value
     *  Content-Length: 349
     *
     * */

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok()
                .header("Content-Type", "application/json")  // Specifies that the response body is JSON
                .header("Cache-Control", "no-cache")        // Prevents caching of the response
                .header("Authorization", "Bearer <token>")  // Auth token (e.g., JWT token)
                .header("Set-Cookie", "sessionId=abc123; Path=/; HttpOnly")  // Sets a session cookie
                .header("X-Custom-Header", "Custom-Value")  // Custom header for additional info
                .header("Content-Length", String.valueOf(products.size()))  // Body size info
                .body(products);
    }

    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(customers);
    }

}
