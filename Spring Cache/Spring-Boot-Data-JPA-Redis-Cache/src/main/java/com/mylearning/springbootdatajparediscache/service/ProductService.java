package com.mylearning.springbootdatajparediscache.service;
import com.mylearning.springbootdatajparediscache.entity.Product;
import com.mylearning.springbootdatajparediscache.repository.ProductRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Cacheable(value = "products", key = "#id")
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @CacheEvict(value = "products", key = "#id")
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    //key = "#product.id" is intended to specify a single cache entry to evict (based on the product.id).
    //allEntries = true removes all entries in the products cache, regardless of the key.
    //allEntries = true: Clears all entries within the products cache, not just one specific entry.
    @CacheEvict(value = "products", allEntries = true)
    public Product saveOrUpdateProduct(Product product) {
        return productRepository.save(product);
    }
//    Whenever saveOrUpdateProduct is called, all entries in the products cache will be removed. This can be useful if:

//    You need to ensure that the cache is fully refreshed because updates could affect multiple cached entries.
//    You are performing bulk updates or complex operations where it's safer to clear the entire cache to avoid stale data.
}