package com.mylearning.springbootmockito;

import com.mylearning.springbootmockito.entity.Product;
import com.mylearning.springbootmockito.repository.ProductRepository;
import com.mylearning.springbootmockito.service.ProductService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// traditional approach
public class ProductServiceTestTraditional {

    @Test
    public void testSaveProduct() {
        ProductRepository realRepository = new ProductRepository();
        ProductService productService = new ProductService(realRepository);

        Product product = new Product("Test Product", 1200.00);
        Product savedProduct = productService.saveProduct(product);

        assertEquals("Test Product", savedProduct.getName());
    }
}


/*

⇒ Why This Might Not Work Well:

* Dependencies like databases are slow: If ProductRepository talks to a real database, your test will depend on the database setup.

* Hard to isolate logic in ProductService: The test's result depends on whether ProductRepository works correctly.

* Limited control over external behavior: You can't easily simulate errors (like database failures) to test edge cases.

*/