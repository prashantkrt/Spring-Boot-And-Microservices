package com.mylearning.springbootmappingmanytmany.experimentRepository;

import com.mylearning.springbootmappingmanytmany.experiment.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
