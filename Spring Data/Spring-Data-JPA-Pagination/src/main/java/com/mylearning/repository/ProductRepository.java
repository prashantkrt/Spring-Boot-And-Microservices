package com.mylearning.repository;

import com.mylearning.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    // Derived Query Methods
    List<Product> findByName(String name);

    List<Product> findByProductType(String productType);

    List<Product> findByNameAndProductType(String name, String productType);

    List<Product> findByPriceOrDescription(double price, String description);

    List<Product> findByDescriptionIsNotNull();

    List<Product> findByDescriptionIsNull();

    //prefix + field + operator
    List<Product> findByPriceGreaterThan(double price);

    List<Product> findByPriceBetween(double lower, double upper);

    List<Product> findByPriceIn(List<Double> prices);

    List<Product> findByPriceLessThan(double price);

    List<Product> findByNameIgnoreCaseContaining(String name);

    void deleteByName(String name);

    @Query("SELECT p FROM Product p WHERE p.price > ?1")
    List<Product> getProductByPrice(double price);

    @Query("SELECT p FROM Product p WHERE p.price > :price")
    List<Product> findProductsByPriceGreaterThan(double price);

    @Query("SELECT p FROM Product p WHERE p.price > :price")
    List<Product> findProductsByPriceGreaterThanWithParamExample(@Param("price") double productPrice);

    @Query("SELECT p FROM Product p WHERE p.productType = :productType ORDER BY p.price DESC")
    List<Product> findProductsByProductTypeOrderByPriceDesc(String productType);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name%")
    List<Product> findProductsByNameContaining(String name);

    @Query(value = "SELECT * FROM Product_Table WHERE product_type = ?1 LIMIT 1", nativeQuery = true)
    Product findFirstByProductType(String productType);

    @Query(value = "SELECT * FROM Product_Table WHERE price < :price", nativeQuery = true)
    List<Product> findProductsByPriceLesserThan(double price);
}


