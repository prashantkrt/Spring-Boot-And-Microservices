package com.mylearning.springbootdatarediscache.controller;

import com.mylearning.springbootdatarediscache.hash.Product;
import com.mylearning.springbootdatarediscache.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }

    @PostMapping
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }
}