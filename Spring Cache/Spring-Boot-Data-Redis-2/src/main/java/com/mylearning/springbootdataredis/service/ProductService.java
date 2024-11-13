package com.mylearning.springbootdataredis.service;


import com.mylearning.springbootdataredis.hash.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private static final String PRODUCT_KEY = "product:";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /*
    *
       {
         "id": "123",
         "name": "Laptop",
         "price": 999.99
         }

      SET product:123 {"id":"123", "name":"Laptop", "price":999.99}
      * The RedisTemplate sends the SET command to the Redis server.
      * The server stores the key-value pair in memory.
      * Redis uses its internal data structures (like a hash table) to store this data.
      * Since Redis is an in-memory store, the data is very fast to read and write.
    */

    // Save or Update a Product
    public void saveProduct(Product product) {
        redisTemplate.opsForValue().set(PRODUCT_KEY + product.getId(), product);
    }

    // Get a Product by ID
    public Product getProductById(String id) {
        return (Product) redisTemplate.opsForValue().get(PRODUCT_KEY + id);
    }

    // Delete a Product by ID
    public void deleteProduct(String id) {
        redisTemplate.opsForValue().getOperations().delete(PRODUCT_KEY + id);
    }

    // Check if Product exists
    public boolean productExists(String id) {
        return redisTemplate.opsForValue().get(PRODUCT_KEY + id) != null;
    }

    // Update a Product by ID
    public void updateProduct(String id, Product updatedProduct) {
        // Retrieve the existing product
        Product existingProduct = (Product) redisTemplate.opsForValue().get(PRODUCT_KEY + id);
        if (existingProduct != null) {
            // Update fields of the existing product as per the updatedProduct
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            // Save the updated product back to Redis
            saveProduct(existingProduct);
        } else {
            throw new RuntimeException("Product with ID " + id + " does not exist.");
        }
    }
}