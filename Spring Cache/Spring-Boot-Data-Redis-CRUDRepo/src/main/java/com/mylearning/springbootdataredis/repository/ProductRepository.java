package com.mylearning.springbootdataredis.repository;

import com.mylearning.springbootdataredis.hash.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, String> {
    // You can define custom queries if needed, otherwise, the basic CRUD methods are available
}
