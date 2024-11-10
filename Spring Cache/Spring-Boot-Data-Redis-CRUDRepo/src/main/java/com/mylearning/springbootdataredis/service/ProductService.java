package com.mylearning.springbootdataredis.service;

import com.mylearning.springbootdataredis.hash.Product;
import com.mylearning.springbootdataredis.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Save a product (creates a new or updates an existing one)
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // Get a product by ID
    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    // Get all products
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Delete a product by ID
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}
