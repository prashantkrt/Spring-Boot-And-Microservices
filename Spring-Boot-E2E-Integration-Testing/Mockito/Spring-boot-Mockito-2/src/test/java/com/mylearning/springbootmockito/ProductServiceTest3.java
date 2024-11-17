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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;


@ExtendWith(MockitoExtension.class)
class ProductServiceTest3 {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void testWhenThenReturnSuccess() {
        // Arrange: Stub the repository method
        Product product = new Product(1L, "Test Product", 100.0);
        Mockito.when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));

        // Act: Call the service method
        Product result = productService.getProductById(1L).orElse(null);

        // Assert: Check if the mock worked as expected
        assertNotNull(result); // Pass
        assertEquals("Test Product", result.getName()); // Pass
    }

    @Test
    void testWhenThenReturnFailure() {
        // Arrange: Stub the repository to return empty
        Mockito.when(productRepository.findById(2L))
                .thenReturn(Optional.empty());

        // Act: Call the service method
        Optional<Product> result = productService.getProductById(2L);

        // Assert: Verify failure (empty Optional)
        assertTrue(result.isEmpty()); // Pass
    }

    @Test
    void testDoReturnWhen() {
        // Arrange: Use doReturn instead of when
        Product product = new Product(1L, "Another Product", 200.0);
        Mockito.doReturn(Optional.of(product)).when(productRepository).findById(1L);

        // Act
        Product result = productService.getProductById(1L).orElse(null);

        // Assert
        assertEquals("Another Product", result.getName()); // Pass
    }

    @Test
    void testVerifyTimes() {
        // Arrange
        Product product = new Product(1L, "Test Product", 100.0);
        Mockito.when(productRepository.save(Mockito.any(Product.class)))
                .thenReturn(product);

        // Act: Save product twice
        productService.saveProduct(product);
        productService.saveProduct(product);

        // Assert: Verify save() was called twice
        Mockito.verify(productRepository, Mockito.times(2)).save(Mockito.any(Product.class)); // Pass
    }

    @Test
    void testVerifyNever() {
        // Act: Do nothing

        // Assert: Verify deleteById was never called
        Mockito.verify(productRepository, Mockito.never()).deleteById(Mockito.anyLong()); // Pass
    }

    @Test
    void testVerifyInOrder() {
        // Arrange
        Product product = new Product(1L, "Ordered Product", 150.0);
        Mockito.when(productRepository.save(Mockito.any(Product.class)))
                .thenReturn(product);

        // Act: Call methods in specific order
        productService.saveProduct(product);
        productService.getAllProducts();

        // Assert: Verify the order of method calls
        InOrder inOrder = Mockito.inOrder(productRepository);
        inOrder.verify(productRepository).save(Mockito.any(Product.class)); // Pass
        inOrder.verify(productRepository).findAll(); // Pass
    }

    @Test
    void testPointOfFailure() {
        // Arrange: Stub save to throw an exception
        Mockito.when(productRepository.save(Mockito.any(Product.class)))
                .thenThrow(new RuntimeException("Save failed"));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            productService.saveProduct(new Product(1L, "Failure Test", 100.0));
        });

        // Output: "Save failed"
        assertEquals("Save failed", exception.getMessage()); // Pass
    }

    @Test
    void testArgumentMatchers() {
        // Arrange
        Mockito.when(productRepository.findById(Mockito.argThat(id -> id > 0)))
                .thenReturn(Optional.of(new Product(1L, "Matching Product", 100.0)));

        // Act
        Product result = productService.getProductById(1L).orElse(null);

        // Assert
        assertNotNull(result); // Pass
        assertEquals("Matching Product", result.getName()); // Pass

        // Point of failure: id <= 0 will return empty
        Optional<Product> failureResult = productService.getProductById(-1L);
        assertTrue(failureResult.isEmpty()); // Pass
    }

    @Test
    void testSpy() {
        // Arrange: Use spy to create a partial mock
        List<String> spyList = Mockito.spy(new ArrayList<>());

        // Act
        spyList.add("Element 1");
        Mockito.when(spyList.size()).thenReturn(100);

        // Assert
        assertEquals(100, spyList.size()); // Pass
        assertEquals("Element 1", spyList.get(0)); // Pass

        // Point of failure: Real methods are called unless stubbed
        Mockito.verify(spyList).add("Element 1"); // Pass
    }
}
