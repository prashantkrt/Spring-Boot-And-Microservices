package com.mylearning.springbootdatajparediscache.repository;

import com.mylearning.springbootdatajparediscache.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}