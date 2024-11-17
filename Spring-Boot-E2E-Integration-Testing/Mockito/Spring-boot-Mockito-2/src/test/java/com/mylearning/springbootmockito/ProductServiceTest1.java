package com.mylearning.springbootmockito;

import com.mylearning.springbootmockito.entity.Product;
import com.mylearning.springbootmockito.repository.ProductRepo;
import com.mylearning.springbootmockito.repository.ProductRepository;
import com.mylearning.springbootmockito.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // enable mockito
public class ProductServiceTest1 {

    // now if we do this, mocks will be injected in the productService
    // mocks will be injected inside productService
    @InjectMocks
    private ProductService productService; //mock object anything we perform using this will not be reflecting inside db

    // productRepository is the dependency of the productService
    @Mock
    private ProductRepository productRepository;


    @Test
    public void addProduct() {
        Product product = new Product();
        product.setPrice(1200.00);
        product.setName("Lamp");

        // will return null since we are not doing with the real object we are using the mock object
        Product productTest = productService.saveProduct(product); // will not reflect in db
        System.out.println(productTest); // null since mock will not make any changes over db
    }

    @Test
    public void addProduct2() {
        Product product = new Product();
        product.setId(1L);
        product.setPrice(1200.00);
        product.setName("Lamp");

        // here we are asking the Mockito to return,
        // we are telling the mock to return the product while productRepository will save the product
        Mockito.when(productRepository.save(product)).thenReturn(product);

        // will return null since we are not doing with the real object we are using the mock object
        Product productTest = productService.saveProduct(product); // will not reflect in db
        System.out.println(productTest); //Product(id=null, name=Lamp, price=1200.0)

        assertNotNull(productTest);
        Assertions.assertEquals(product.getId(), productTest.getId());
        Assertions.assertTrue(productTest.getId() == 1);
        //or
        assertEquals(product.getPrice(), productTest.getPrice());
        assertEquals(product.getName(), productTest.getName());
        assertEquals(1, productTest.getId());
    }

    @Test
    void testCreateProduct() {
        // Arrange
        Product product = new Product(1L, "Sample Product", 100.0);
        Mockito.when(productRepository.save(product)).thenReturn(product);

        // Act
        Product result = productService.createProduct(product);
        System.out.println(result); //Product(id=1, name=Sample Product, price=100.0)

        // Assert
        Assertions.assertEquals(product, result);
        verify(productRepository).save(product); // Ensures `save` was called
    }

    @Test
    public void testDeleteProduct() {
        Long productId = 1L;
        productService.deleteProduct(productId);

        // Verify that `deleteById` was called once with the correct argument
        verify(productRepository).deleteById(productId);
        verify(productRepository, times(1)).deleteById(productId);

        // Optionally, verify that no other methods were called on the mock
        Mockito.verifyNoMoreInteractions(productRepository);
    }

    @Test
    public void testDeleteProductSuccessfully() {
        Long productId = 1L;
        //you use doNothing to specify that the void method should do nothing when called.
        doNothing().when(productRepository).deleteById(productId);

        //If you want to mock a void method that throws an exception, you can use doThrow:
        //doThrow(new RuntimeException("Error occurred")).when(productRepository).deleteById(1L);
        productService.deleteProduct(productId);
        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteProduct3_withDoNothing() {
        // Step 1: Mock the service containing deleteProduct3
        ProductService mockService = Mockito.mock(ProductService.class);

        //Using doNothing, we mock this method to override its behavior so it doesn’t throw an exception during the test.
        // Step 2: Stub the method to do nothing
        doNothing().when(mockService).deleteProduct3(1L);

        // Step 3: Call the method to verify no exception is thrown
        mockService.deleteProduct3(1L);

        // Step 4: Verify the method was called
        verify(mockService, times(1)).deleteProduct3(1L);
    }

    //To mock void methods or any methods with specific exception-throwing behavior.
    //Useful when you want to simulate error scenarios and validate your application's error-handling logic.
    @Test
    public void testDeleteProduct3_withDoThrow() {
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
    void testUnexpectedInteraction() {

        productService.findProductById(1L);
        productService.findProductById(2L);
        //productService.findProductById(3L); fail case

        // Simulate calling save() method
        productRepository.save(new Product( "Another Product",3L));

        // Verify specific interactions
        verify(productRepository).findById(1L);
        verify(productRepository).findById(2L);
        verify(productRepository).save(new Product("Another Product",3L)); // Verify save() method was called

        // Now we can call verifyNoMoreInteractions without failure
        verifyNoMoreInteractions(productRepository); // Passes because we've verified all interactions
    }

    @Test
    void testUnexpectedInteraction2() {
        productService.findProductById(1L);
        productService.findProductById(2L);

        // Verify specific interactions
        verify(productRepository).findById(1L);
        verify(productRepository).findById(2L);

        // Now let's simulate an unexpected interaction
        productRepository.save(new Product("Another Product",3L));

        // This will fail because an unexpected method was called
        verifyNoMoreInteractions(productRepository); // Fails here
    }

    @Test
    void testProductService() {

        productService.findProductById(1L);
        productService.findProductById(2L);

        // Verify interactions with the mock
        verify(productRepository).findById(1L);  // First verification
        verify(productRepository).findById(2L);  // Second verification

        // Now, we ensure there are no additional interactions
        verifyNoMoreInteractions(productRepository); // Ensures no other methods are called after these verifications
    }

    @Test
    void testGetProductById() {

        Product product = new Product(1L, "Sample Product", 100.0);
        // void method
        // Mock the delete method using doReturn (although it's void, we simulate the behavior)
        doReturn(null).when(productRepository).delete(product);

        // Mock the return value of getProductById using doReturn
        // doReturn(product).when(productRepository).getProductById(1L);
        // Product product = productService.getProductById(1L);

        productService.deleteProduct(product);
        verify(productRepository).delete(product); // Verify that the method was called
    }

//    mock() creates a full mock of the object where all methods are mocked (i.e., no actual behavior is executed).
//    spy() creates a partial mock where you can specify which methods to mock, and the other methods will use their real implementation.

//    @Test
//    void testAddProductWithSpy() {
//
//        // Create a mock ProductRepository interface
//        ProductRepository mockRepo = mock(ProductRepository.class);
//
//        // Create a spy of the mock (we could use a real instance here if needed)
//        ProductRepository spyRepo = spy(mockRepo);
//
//        // Create a ProductService with the spy
//        ProductService productService = new ProductService(spyRepo);
//
//        // Define behavior for mock methods (we'll mock 'save' method here)
//        Product product = new Product("Product A",1L);
//        doReturn(product).when(spyRepo).save(any(Product.class)); // Mock save method
//
//        // Test addProduct method with mocked behavior
//        Product result = productService.addProduct(product);
//
//        // Verify the interaction and assert result
//        assertNotNull(result);
//        assertEquals("Product A", result.getName());
//
//        // Verify that the 'save' method was called once
//        verify(spyRepo, times(1)).save(any(Product.class));
//    }

//    Use Mockito.spy() when you want to partially mock an object, keeping the default behavior of most methods and only mocking the methods you care about for the test.
//    It's particularly useful when you want to test a real object, but mock some specific interactions (like a method call) without altering the entire behavior.

 //   For a repository interface like ProductRepository, if there are no custom methods, the methods inherited from JpaRepository (e.g., findById(), save(), etc.) will work as they are, and you can mock them if needed.

}
