package com.mylearning.springbootmockito.service;

import com.mylearning.springbootmockito.entity.Product;
import com.mylearning.springbootmockito.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(long l) {
        return productRepository.findById(l);
    }


    // just to understand unit testing using mockito
    public Product createProduct(Product product) {
        if (product.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        return productRepository.save(product);
    }

    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

    public String deleteProduct2(long id) {
        productRepository.deleteById(id);
        return "Deleted product with id: " + id;
    }

    // Real implementation throws an exception
    public void deleteProduct3(Long id) {
        throw new UnsupportedOperationException("Cannot delete");
    }


    private boolean validate(Long id) {
        if (id < 0)
            return false;
        else return true;
    }

    public Product addProduct(Product product) {
        if (validate(product.getId())) {
            return productRepository.save(product);
        } else
            throw new RuntimeException("Product Id cannot be negative");
    }

    public void findProductById(long l) {
        productRepository.findById(l);
    }
}
