package com.mylearning.springbootdataredis.controller;

import com.mylearning.springbootdataredis.hash.Product;
import com.mylearning.springbootdataredis.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Endpoint to save or update a product
    @PostMapping
    public ResponseEntity<String> saveProduct(@RequestBody Product product) {
        productService.saveProduct(product);
        return ResponseEntity.ok("Product saved successfully");
    }

    // Endpoint to get a product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    // Endpoint to delete a product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    // Endpoint to check if a product exists
    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> productExists(@PathVariable String id) {
        boolean exists = productService.productExists(id);
        return ResponseEntity.ok(exists);
    }
}