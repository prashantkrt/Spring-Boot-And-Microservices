package com.mylearning.springbootmockito;

import com.mylearning.springbootmockito.entity.Product;
import com.mylearning.springbootmockito.repository.ProductRepository;
import com.mylearning.springbootmockito.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest5ExceptionScenarios {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void testDelete() {
        // Step 1: Mock the ProductService
        ProductService mockService = Mockito.mock(ProductService.class);

        // Step 2: Stub deleteProduct3 to throw an exception
        doThrow(new UnsupportedOperationException("Cannot delete"))
                .when(mockService).deleteProduct3(1L);

        // Step 3: Verify the exception is thrown when the method is called
        UnsupportedOperationException exception = assertThrows(
                UnsupportedOperationException.class,
                () -> mockService.deleteProduct3(1L)
        );

        // Step 4: Validate the exception message
        assertEquals("Cannot delete", exception.getMessage());

        // Step 5: Verify the method was called once
        verify(mockService, times(1)).deleteProduct3(1L);
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product();
        product.setId(201L);
        product.setPrice(-1.00);
        product.setName("Test Product");

        IllegalArgumentException exception =assertThrows(IllegalArgumentException.class, () -> {
            productService.createProduct(product);
        });

        assertEquals("Price cannot be negative", exception.getMessage());
    }

    @Test
    public void testAddProduct() throws Exception {
        Product product = new Product();
        product.setId(-202L);
        product.setPrice(100.00);
        product.setName("Test Product");

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productService.addProduct(product);
        });

        assertEquals("Product Id cannot be negative", exception.getMessage());
    }
}
