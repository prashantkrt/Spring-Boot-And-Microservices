package com.mylearning.controller;

import com.mylearning.entity.Product;
import com.mylearning.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public Product addProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @GetMapping("/fetch")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/fetch/{id}")
    public Product getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @PutMapping("/modify/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product productRequest) {
        return productService.updateProduct(id, productRequest);
    }

    @DeleteMapping("/remove/{id}")
    public long deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }

    @GetMapping("/history/{id}")
    public void getRevisionDetails(@PathVariable long id) {
        productService.printProductHistory(id);
    }
}
