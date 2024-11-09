package com.mylearning.springbootcache.service;

import com.mylearning.springbootcache.entity.Product;
import com.mylearning.springbootcache.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).get();
    }

    public Product updateProduct(int id, Product productRequest) {
        Product existingProduct = productRepository.findById(id).get(); // DB
        existingProduct.setName(productRequest.getName());
        existingProduct.setDescription(productRequest.getDescription());
        existingProduct.setPrice(productRequest.getPrice());
        existingProduct.setProductType(existingProduct.getProductType());
        return productRepository.save(existingProduct);
    }

    public long deleteProduct(int id) {
        long countBefore = productRepository.count();
        productRepository.deleteById(id);
        long countAfter = productRepository.count();
        return countBefore - countAfter;
    }
}
