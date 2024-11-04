package com.mylearning.repository;

import com.mylearning.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {
    // Find By Single Attribute
    List<Product> findByName(String name);
    List<Product> findByPrice(double price);
    List<Product> findByProductType(String productType);

    // Find By Multiple Attributes (AND Condition)
    List<Product> findByNameAndProductType(String name, String productType);
    List<Product> findByNameAndPrice(String name, double price);
    List<Product> findByProductTypeAndDescription(String productType, String description);

    // Find By Multiple Attributes (OR Condition)
    List<Product> findByNameOrProductType(String name, String productType);
    List<Product> findByPriceOrDescription(double price, String description);

    // Find By Attribute With Comparison
    List<Product> findByPriceGreaterThan(double price);
    List<Product> findByPriceLessThan(double price);
    List<Product> findByPriceBetween(double startPrice, double endPrice);
    List<Product> findByPriceGreaterThanEqual(double price);
    List<Product> findByPriceLessThanEqual(double price);

    // Find By Attribute with Sorting
    List<Product> findByNameOrderByPriceAsc(String name);
    List<Product> findByProductTypeOrderByPriceDesc(String productType);
    List<Product> findByProductTypeOrderByNameAsc(String productType);

    // Find By Attribute Using LIKE (Pattern Matching)
    List<Product> findByNameStartingWith(String prefix);
    List<Product> findByNameEndingWith(String suffix);
    List<Product> findByNameContaining(String infix);
    List<Product> findByDescriptionContaining(String description);

    // Find Top or First Records
    Optional<Product> findTopByOrderByPriceDesc();
    Optional<Product> findTopByOrderByPriceAsc();
    Optional<Product> findFirstByProductTypeOrderByPriceDesc(String productType);
    List<Product> findTop3ByProductTypeOrderByPriceDesc(String productType);

    // Count and Existence Checks
    long countByProductType(String productType);
    long countByPriceGreaterThan(double price);
    boolean existsByName(String name);
    boolean existsByPriceLessThan(double price);

    // Delete By Attribute
    void deleteByName(String name);
    void deleteByProductType(String productType);
    void deleteByPriceLessThan(double price);
    void deleteByProductTypeAndPriceLessThan(String productType, double price);

    // Find By Null or Non-Null Attribute
    List<Product> findByDescriptionIsNull();
    List<Product> findByDescriptionIsNotNull();

    // Complex Combined Queries
    List<Product> findByProductTypeAndPriceGreaterThanOrderByPriceAsc(String productType, double price);
    List<Product> findByNameContainingAndPriceLessThan(String name, double price);
    List<Product> findByProductTypeAndDescriptionIsNotNullOrderByPriceDesc(String productType);

}
