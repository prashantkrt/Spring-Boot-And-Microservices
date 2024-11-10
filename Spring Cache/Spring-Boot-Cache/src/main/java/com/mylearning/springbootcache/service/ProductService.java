package com.mylearning.springbootcache.service;

import com.mylearning.springbootcache.entity.Product;
import com.mylearning.springbootcache.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

//    Loading the data first time while starting the application
//    @PostConstruct
//    public void initDB() {
//        //new Random().nextInt(5000): Generates a random integer in the range from 0 (inclusive) up to but not including 5000.
//        //The resulting random integer will be in the range 0 to 4999.
//        productRepository.saveAll(IntStream.rangeClosed(1, 10000).mapToObj(i -> new Product("Product" + i, new Random().nextInt(5000), "Product Description => " + i, "product type => " + i))
//                .collect(Collectors.toList()));
//    }

    //The @CachePut annotation in Spring is used to update or refresh the cache with the latest method result every time the annotated method is called.
    // Storing the data into the Cache if new data added
    // ConcurrentHashMap key and Value
    // key =>  is value from the entity which we can use it as key
    // value => cache name
    //@CachePut(value = "productsCache", key = "#product.id") or @CachePut(cacheNames = "productsCache", key = "#product.id") both are same
    //@CachePut(value = "productsCache", key = "#product.id")
    //@CachePut(value = "productsCache", key = "#product.id", condition = "#product.price > 100", unless = "#result == null")
    /*
     * condition = "#product.price > 100": Caches the result only if the product's price is greater than 100.
     * unless = "#result == null": Excludes caching if the method returns null.
     * */
    //productsCache is the name of the cache where the results of the method will be stored.
    @CachePut(cacheNames = "productsCache", key = "#product.id")
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // will reduce the fetch timing due to caching
    // The first time you call getProducts(), the method will fetch all the data from the database and store it in the cache (productsCache).
    // When you call getProducts() again, it will return the data from the cache, not including the new data, because the cache was never updated with the new data.
    // When using @Cacheable, the cache will be populated only the first time the method is called (assuming no data is already present in the cache).
    // If there is already data in the cache and you add new data without updating the cache (for example, by not using @CachePut), the cache will not automatically include the new data.
    @Cacheable(cacheNames = "productsCache")
    public List<Product> getProducts() {
        log.info("Inside DB product service => getProducts");
        return productRepository.findAll();
    }

    //for read operations
    @Cacheable(cacheNames = "productsCache",key = "#id")
    public Product getProductById(int id) {
        return productRepository.findById(id).get();
    }

    //for save and update operations
    @CachePut(value="productsCache",key = "#id")
    public Product updateProduct(int id, Product productRequest) {
        Product existingProduct = productRepository.findById(id).get(); // DB
        existingProduct.setName(productRequest.getName());
        existingProduct.setDescription(productRequest.getDescription());
        existingProduct.setPrice(productRequest.getPrice());
        existingProduct.setProductType(existingProduct.getProductType());
        return productRepository.save(existingProduct);
    }

    // for delete operation it will update the cache memory
    @CacheEvict(value="productsCache",key = "#id")
    public long deleteProduct(int id) {
        long countBefore = productRepository.count();
        productRepository.deleteById(id);
        long countAfter = productRepository.count();
        return countBefore - countAfter;
    }
}

/*
Note:
// Uses cache "productsCache" for retrieving data, checks only this cache
@Cacheable(cacheNames = "productsCache", key = "#id")
public Product getProductById(Long id) {
    return productRepository.findById(id).orElse(null);
}

// Updates the cache "updatedProductsCache" with the latest data, does not affect "productsCache"
@CachePut(cacheNames = "updatedProductsCache", key = "#product.id")
public Product updateProduct(Product product) {
    return productRepository.save(product);
}*/
