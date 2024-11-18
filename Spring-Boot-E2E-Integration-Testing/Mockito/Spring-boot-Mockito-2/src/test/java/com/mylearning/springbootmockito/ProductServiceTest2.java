package com.mylearning.springbootmockito;

import com.mylearning.springbootmockito.entity.Product;
import com.mylearning.springbootmockito.repository.ProductRepository;
import com.mylearning.springbootmockito.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest2 {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void testSaveProduct() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(100.0);

        Mockito.when(productRepository.save(Mockito.any(Product.class)))
                .thenReturn(product);

        Product savedProduct = productService.saveProduct(product);

        assertNotNull(savedProduct);
        assertEquals("Test Product", savedProduct.getName());
        assertEquals(100.0, savedProduct.getPrice());

        Mockito.verify(productRepository, Mockito.times(1)).save(product);
    }

    @Test
    void testGetAllProducts() {
        List<Product> productList = Arrays.asList(
                new Product("Product 1", 50.0),
                new Product("Product 2", 150.0)
        );

        Mockito.when(productRepository.findAll()).thenReturn(productList);

        List<Product> products = productService.getAllProducts();

        assertEquals(2, products.size());
        Mockito.verify(productRepository, Mockito.times(1)).findAll();
    }

/*

    Create the InOrder object:
    InOrder inOrder = inOrder(mock1, mock2, ...);

    Verify method calls in order:
    inOrder.verify(mock).method1();
    inOrder.verify(mock).method2();
*/

    @Test
    void testOrderOfInteractions() {
        // Setup the mock behavior
        Long productId = 1L;
        Product newProduct = new Product("New Product",productId);

        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.empty()); // No product exists

        // Call the method that has a sequence of calls
        productService.checkAndAddProduct(productId, newProduct);

        // Create an InOrder verifier
        InOrder inOrder = inOrder(productRepository);

        // Verify the order of method calls
        inOrder.verify(productRepository).findById(productId); // First, it should check if the product exists
        inOrder.verify(productRepository).save(newProduct);    // Second, it should save the new product
    }

    @Test
    void testOrderFailsIfOutOfOrder() {
        // Incorrect behavior for demonstration
        Long productId = 1L;
        Product newProduct = new Product("New Product",productId);

        // Simulate saving before checking (wrong order)
        productRepository.save(newProduct);
        productRepository.findById(productId);

        // Create an InOrder verifier
        InOrder inOrder = inOrder(productRepository);

        // This test will fail because the order is wrong
        inOrder.verify(productRepository).findById(productId); // Should be first
        inOrder.verify(productRepository).save(newProduct);    // Should be second
    }


    @Test
    void testOrderOfInteractionsWithInorderDummySenario() {
        // Mock a repository
        ProductRepository productRepository = mock(ProductRepository.class);

        Long productId = 1L;
        // Call methods in a specific order
        productRepository.findById(1L); // First: Fetch product by ID
        productRepository.save(new Product("New Product",productId)); // Second: Save the product

        // Create an InOrder verifier for productRepository
        InOrder inOrder = inOrder(productRepository);

        // Verify the methods were called in the correct order
        inOrder.verify(productRepository).findById(1L); // Must be called first
        inOrder.verify(productRepository).save(Mockito.any(Product.class)); // Must be called second
    }
}
