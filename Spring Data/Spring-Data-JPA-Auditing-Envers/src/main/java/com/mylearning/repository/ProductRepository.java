package com.mylearning.repository;

import com.mylearning.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface ProductRepository extends JpaRepository<Product, Long> , RevisionRepository<Product, Long, Integer> {
}
