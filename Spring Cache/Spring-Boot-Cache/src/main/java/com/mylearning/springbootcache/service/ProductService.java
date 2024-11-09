package com.mylearning.springbootcache.service;

import com.mylearning.springbootcache.entity.Product;
import com.mylearning.springbootcache.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void initDB() {
        //new Random().nextInt(5000): Generates a random integer in the range from 0 (inclusive) up to but not including 5000.
        //The resulting random integer will be in the range 0 to 4999.
        productRepository.saveAll(IntStream.rangeClosed(1, 10000).mapToObj(i -> new Product("Product" + i, new Random().nextInt(5000), "Product Description => " + i, "product type => " + i))
                .collect(Collectors.toList()));
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
