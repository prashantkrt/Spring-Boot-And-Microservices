package com.mylearning.multidb.repository.db2;

import com.mylearning.multidb.model.productModel.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
