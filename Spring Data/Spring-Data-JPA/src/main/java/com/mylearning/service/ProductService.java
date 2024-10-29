package com.mylearning.service;

import com.mylearning.entity.Product;
import com.mylearning.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).get();
    }
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    public List<Product> getProductByName(String name) {
        return productRepository.findByName(name);
    }

    public List<Product> getProductsByType(String productType) {
        return productRepository.findByProductType(productType);
    }

    public List<Product> getProductsByNameAndProductType(String name, String productType) {
        return productRepository.findByNameAndProductType(name, productType);
    }

}
