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

    public Product getProductById(long id) {
        return productRepository.findById(id).get();
    }

    public Product updateProduct(long id, Product productRequest) {
        Product existingProduct = productRepository.findById(id).get();
        existingProduct.setName(productRequest.getName());
        existingProduct.setDescription(productRequest.getDescription());
        existingProduct.setPrice(productRequest.getPrice());
        existingProduct.setProductType(existingProduct.getProductType());
        return productRepository.save(existingProduct);
    }

    public long deleteProduct(long id) {
        productRepository.deleteById(id);
        return productRepository.count();
    }


    // Revision or version details fetch
    public void printProductHistory(Long productId) {
        productRepository.findRevisions(productId).forEach(revision -> {
            System.out.println("Revision Number: " + revision.getRevisionNumber());
            System.out.println("Revision Timestamp: " + revision.getRevisionInstant());
            System.out.println("Product Data: " + revision.getEntity());
        });
    }

}
