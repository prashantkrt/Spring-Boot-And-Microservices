package com.mylearning.springbootdatarediscache.service;

import com.mylearning.springbootdatarediscache.hash.Product;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductService {

    // dummy database usecase :)
    private Map<String, Product> productDatabase = new HashMap<>();

    // Example of @Cacheable
    @Cacheable(value = "products", key = "#id")
    public Product getProductById(String id) {
        System.out.println("Fetching product from database...");
        return productDatabase.get(id);
    }

    // Example of @CachePut (used to update the cache)
    @CachePut(value = "products", key = "#product.id")
    public Product updateProduct(Product product) {
        productDatabase.put(product.getId(), product);
        return product;
    }

    // Example of @CacheEvict (used to clear the cache)
    @CacheEvict(value = "products", key = "#id")
    public void deleteProduct(String id) {
        productDatabase.remove(id);
    }

    // Adding a product to our simulated database (no caching here)
    public void addProduct(Product product) {
        productDatabase.put(product.getId(), product);
    }
}