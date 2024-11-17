package com.mylearning.springbootmockito.repository;

import com.mylearning.springbootmockito.entity.Product;

public interface ProductRepo {
    Product save(Product product); // Saves a product
}
