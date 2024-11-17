package com.mylearning.springbootmockito.repository;

import com.mylearning.springbootmockito.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProductRepository extends JpaRepository<Product, Long> {
    // You can define custom queries here if needed
    Product findByName(String name);
}
