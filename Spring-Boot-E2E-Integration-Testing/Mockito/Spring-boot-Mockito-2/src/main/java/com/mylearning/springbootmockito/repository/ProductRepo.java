package com.mylearning.springbootmockito.repository;

import com.mylearning.springbootmockito.entity.Product;

public class ProductRepo {
    public Product save(Product product) {
        // Simulating save operation (e.g., saving to a database)
        return product;
    }

    public Product findById(Long id) {
        // Simulating find operation (e.g., fetching from a database)
        return new Product("Product " + id, id);
    }
}
