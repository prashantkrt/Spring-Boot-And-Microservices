package com.mylearning.springbootmockito;

import com.mylearning.springbootmockito.entity.Product;
import com.mylearning.springbootmockito.repository.ProductRepository;
import com.mylearning.springbootmockito.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

        Assertions.assertNotNull(productTest);
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


}
