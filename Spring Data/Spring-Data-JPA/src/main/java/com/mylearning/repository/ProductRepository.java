package com.mylearning.repository;

import com.mylearning.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    // Derived Query Methods
    List<Product> findByName(String name);
    List<Product> findByPriceLessThan(double price);
    List<Product> findByProductType(String productType);
    List<Product> findByNameAndProductType(String name, String productType);
    void deleteByName(String name);
    List<Product> findByDescriptionIsNotNull();
    List<Product> findByPriceOrDescription(double price, String description);


    // Custom Query Methods
    @Query("SELECT p FROM Product p WHERE p.price > :price")
    List<Product> findProductsByPriceGreaterThan(double price);

    @Query("SELECT p FROM Product p WHERE p.productType = :productType ORDER BY p.price DESC")
    List<Product> findProductsByProductTypeOrderByPriceDesc(String productType);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name%")
    List<Product> findProductsByNameContaining(String name);

    @Query(value = "SELECT * FROM Product_Table WHERE product_type = ?1 LIMIT 1", nativeQuery = true)
    Product findFirstByProductType(String productType);


}
//1. Standard CRUD Methods (Provided by JpaRepository)
//By extending JpaRepository<Product, Integer>, we get the following basic CRUD operations:
//
// a.) save(Product product): Product
//Description: Saves the given product to the database. If the product already exists (determined by the primary key), it updates the record; otherwise, it inserts a new one.
//Return Type: Product (the saved entity)
//
// b.) findById(Integer id): Optional<Product>
//Description: Retrieves a product by its ID.
//Return Type: Optional<Product> (contains the product if found, otherwise empty)
//
// c.) findAll(): List<Product>
//Description: Fetches all products in the database.
//Return Type: List<Product> (list of all products)
//
// d.) deleteById(Integer id): void
//Description: Deletes the product with the given ID.
//Return Type: void
//
// e.) count(): long
//Description: Returns the count of all products in the database.
//Return Type: long
