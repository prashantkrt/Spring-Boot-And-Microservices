package com.mylearning.springbootmappingmanytmany.experimentRepository;

import com.mylearning.springbootmappingmanytmany.experiment.Category;
import com.mylearning.springbootmappingmanytmany.experiment.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
//
//@Component
//public class TestExecuteExperiment1 implements CommandLineRunner {
//
//    @Autowired
//    private CategoryRepository categoryRepository;
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        // Product-1
//        Product product1 = new Product();
//        product1.setProductName("Product 1");
//        product1.setProductDescription("Product 1");
//        product1.setProductPrice(100.00);
//
//        // Product-2
//        Product product2 = new Product();
//        product2.setProductName("Product 2");
//        product2.setProductDescription("Product 2");
//        product2.setProductPrice(200.00);
//
//        // Product-3
//        Product product3 = new Product();
//        product3.setProductName("Product 3");
//        product3.setProductDescription("Product 3");
//        product3.setProductPrice(300.00);
//
//        productRepository.save(product1);
//        productRepository.save(product2);
//        productRepository.save(product3);
//
//        // Category-1
//        Category category1 = new Category();
//        category1.setCategoryName("Category 1");
//        category1.setCategoryDescription("Category 1");
//
//        // Category-2
//        Category category2 = new Category();
//        category2.setCategoryName("Category 2");
//        category2.setCategoryDescription("Category 2");
//
//        // Category-3
//        Category category3 = new Category();
//        category3.setCategoryName("Category 3");
//        category3.setCategoryDescription("Category 3");
//
//        List<Product> category1Products = category1.getProducts();
//        category1Products.add(product1);
//        category1Products.add(product2);
//        //category1.setProducts(category1Products);
//
//        List<Product> category2Products = category2.getProducts();
//        category2Products.add(product1);
//        category2Products.add(product3);
//        //category2.setProducts(category2Products);
//
//        List<Product> category3Products = category3.getProducts();
//        category3Products.add(product1);
//        category3Products.add(product2);
//        category3Products.add(product3);
//        //category3.setProducts(category3Products);
//
//        categoryRepository.save(category1);
//        categoryRepository.save(category2);
//        categoryRepository.save(category3);
//
//
//    }
//
//}
