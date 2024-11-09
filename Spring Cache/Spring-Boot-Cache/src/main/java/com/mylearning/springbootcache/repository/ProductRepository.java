package com.mylearning.springbootcache.repository;

import com.mylearning.springbootcache.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
