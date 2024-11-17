package com.mylearning.springbootmockito.repository;

import com.mylearning.springbootmockito.entity.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository{
    public Product save(Product product) {
        // Imagine this interacts with a real database
        return product;
    }
}
