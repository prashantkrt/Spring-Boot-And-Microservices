package com.mylearning.springbootmappingonetoone.experimentRepository;

import com.mylearning.springbootmappingonetoone.experiment.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
