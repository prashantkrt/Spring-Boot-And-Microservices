package com.mylearning.springbootmockito;

//Mockito solves these problems by mocking the dependencies.
//Instead of using a real ProductRepository, you use a fake object (a "mock") that you control entirely.


/*
How It Works in Mockito:
You "tell" the mock how to behave (when(...).thenReturn(...)).
You test if your service interacts with the mock correctly.
The focus is on testing only the logic in the service, without depending on the real repository.

When you use Mockito, you are testing the logic of the service class while:
Simulating predictable behavior of the dependencies.
Ensuring correct interactions with the dependencies (using verify).
*/

import com.mylearning.springbootmockito.entity.Product;
import com.mylearning.springbootmockito.repository.ProductRepository;
import com.mylearning.springbootmockito.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ProductServiceTestMockito {

    //what's happening :)
    //ProductService correctly passes the product to the repository.
    //ProductService validates the input (rejects empty names).
    //The repository’s save method is called once with the right arguments.
    //The repository’s save method is not called when validation fails.
    //We are focusing on the service logic
    @Test
    public void testSaveProductSuccess() {
        // Step 1: Create a mock for the ProductRepository
        ProductRepository mockRepository = Mockito.mock(ProductRepository.class);

        // Step 2: Define mock behavior
        Product product = new Product("Test Product");
        when(mockRepository.save(product)).thenReturn(product);

        // Step 3: Inject the mock into ProductService
        ProductService productService = new ProductService(mockRepository);

        // Step 4: Call the service method
        Product savedProduct = productService.saveProduct(product);

        // Step 5: Assert the result
        assertEquals("Test Product", savedProduct.getName());

        // Step 6: Verify that the repository's save method was called
        verify(mockRepository).save(product);
    }

    @Test
    public void testSaveProductWithEmptyName() {
        // Step 1: Create a mock for the ProductRepository
        ProductRepository mockRepository = Mockito.mock(ProductRepository.class);

        // Step 2: Define invalid product
        Product product = new Product(""); // Empty name

        // Step 3: Inject the mock into ProductService
        ProductService productService = new ProductService(mockRepository);

        // Step 4: Expect an exception when the service method is called
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productService.saveProduct(product);
        });

        // Step 5: Verify exception message
        assertEquals("Product name cannot be empty", exception.getMessage());

        // Step 6: Verify that the repository's save method was NOT called
        verify(mockRepository, never()).save(product);
    }
}
