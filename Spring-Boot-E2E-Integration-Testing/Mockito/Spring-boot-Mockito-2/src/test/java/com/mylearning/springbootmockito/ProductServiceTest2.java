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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

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
}
