//package com.mylearning.springbootmappingmanytmany.experimentRepository;
//
//import com.mylearning.springbootmappingmanytmany.experiment.Category;
//import com.mylearning.springbootmappingmanytmany.experiment.Product;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//@Component
//public class TestExecuteExperiment2 implements CommandLineRunner {
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
//        // Products
//        Product product1 = new Product("Product 1", "Description 1", 100.00);
//        Product product2 = new Product("Product 2", "Description 2", 200.00);
//        Product product3 = new Product("Product 3", "Description 3", 300.00);
//
//        // Categories
//        Category category1 = new Category("Category 1", "Description 1");
//        Category category2 = new Category("Category 2", "Description 2");
//        Category category3 = new Category("Category 3", "Description 3");
//
//        category1.getProducts().add(product1);
//        category1.getProducts().add(product2);
//
//        category2.getProducts().add(product1);
//        category2.getProducts().add(product3);
//
//        category3.getProducts().add(product1);
//        category3.getProducts().add(product2);
//        category3.getProducts().add(product3);
//
//        categoryRepository.save(category1);
//        categoryRepository.save(category2);
//        categoryRepository.save(category3);
//
//
//        //Not working it should have done the auto save of Product as well but getting error
//        // detached entity passed to persist: com.mylearning.springbootmappingmanytmany.experiment.Product
//    }
//
//}
