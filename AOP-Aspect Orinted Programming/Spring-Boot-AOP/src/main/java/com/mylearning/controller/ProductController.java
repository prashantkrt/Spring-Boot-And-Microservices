package com.mylearning.controller;

import com.mylearning.entity.Product;
import com.mylearning.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Create or update a product
    @PostMapping
    public Product createOrUpdateProduct(@RequestBody Product product) {
        return productService.saveOrUpdateProduct(product);
    }

    // Get all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Get product by ID
    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    // Delete product by ID
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    //dummy method
    @GetMapping("/getProduct")
    public String getProduct() {
        // Some business logic
        return "Product details";
    }
}