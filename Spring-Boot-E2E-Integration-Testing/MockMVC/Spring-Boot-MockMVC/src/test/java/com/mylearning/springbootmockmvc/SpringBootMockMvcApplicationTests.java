package com.mylearning.springbootmockmvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mylearning.springbootmockmvc.entity.Product;
import com.mylearning.springbootmockmvc.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.Optional;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringBootMockMvcApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;


//    No need to manually do it use @Autowired
//    @BeforeEach
//    public void setup() {
//        this.mockMvc = MockMvcBuilders.standaloneSetup(ProductController.class).build();
//    }

    @Test
    public void addProduct() throws Exception {

        // URL → /addProduct
        // HTTP → POST
        // Request and Response → Product (JSON String)

        Product product = new Product();
        product.setName("test");
        product.setPrice(1200.00);

        // Mock service response
        Mockito.when(productService.addProduct(any(Product.class))).thenReturn(new Product(1L, "test", 1200.00));

        // do the postman like call
        mockMvc.perform(MockMvcRequestBuilders.post("/products/addProduct")
                        .content(convertObjectToJson(product))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("test"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(1200.00));
    }

    @Test
    public void addProduct2() throws Exception {
        Product product = new Product(null, "test", 1200.00);

        // Mock service response
        Mockito.when(productService.addProduct(any(Product.class))).thenReturn(new Product(1L, "test", 1200.00));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/products/addProduct")
                        .content(convertObjectToJson(product))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        //Response: {"id":1,"name":"test","price":1200.0}
        System.out.println("Response: " + result.getResponse().getContentAsString());
    }

    private String convertObjectToJson(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetProductById_ProductExists() throws Exception {
        // Mock the service response for an existing product
        Product mockProduct = new Product(1L, "Test Product", 100.0);
        Mockito.when(productService.getProductById(1L)).thenReturn(Optional.of(mockProduct));

        // Perform the GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/products/1")
                        .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test Product"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(100.0));
    }

    @Test
    public void testGetProductById_ProductNotFound() throws Exception {
        // Mock the service response for a non-existing product
        Mockito.when(productService.getProductById(1L)).thenReturn(Optional.empty());

        // Perform the GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/products/1")
                        .accept("application/json"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testFindAllProducts_ReturnsProducts() throws Exception {
        // Mock the service response
        List<Product> mockProducts = List.of(
                new Product(1L, "Product 1", 100.0),
                new Product(2L, "Product 2", 200.0)
        );
        Mockito.when(productService.findAllProducts()).thenReturn(mockProducts);

        // Perform the GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/products/all")
                        .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Product 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Product 2"));
    }

    @Test
    public void testFindAllProducts_NoProducts() throws Exception {
        // Mock the service response for an empty list
        Mockito.when(productService.findAllProducts()).thenReturn(Collections.emptyList());

        // Perform the GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/products/all")
                        .accept("application/json"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteProduct_Success() throws Exception {
        // Mock the service behavior (do nothing for delete)
        Mockito.doNothing().when(productService).deleteProduct(1L);

        // Perform the DELETE request
        mockMvc.perform(MockMvcRequestBuilders.delete("/products/1"))
                .andExpect(status().isNoContent());

        // Verify the service method was called with the correct argument
        Mockito.verify(productService, times(1)).deleteProduct(1L);
    }

    @Test
    public void testDeleteProduct_NotFound() throws Exception {
        // Mock the service to throw an exception for a non-existent product
        Mockito.doThrow(new RuntimeException("Product not found")).when(productService).deleteProduct(2L);

        // Perform the DELETE request
        mockMvc.perform(MockMvcRequestBuilders.delete("/products/2"))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("Product not found"));

        // Verify the service method was called with the correct argument
        Mockito.verify(productService, times(1)).deleteProduct(2L);
    }

    @Test
    public void testUpdateProduct_Success() throws Exception {
        Long productId = 1L;
        Product existingProduct = new Product(productId, "Old Product", 1000.0);
        Product updatedProduct = new Product(productId, "Updated Product", 1500.0);

        // Mock service behavior
        Mockito.when(productService.updateProduct(Mockito.eq(productId), any(Product.class))).thenReturn(updatedProduct);

        // Perform the PUT request
        mockMvc.perform(MockMvcRequestBuilders.put("/products/" + productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedProduct)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(productId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Updated Product"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(1500.0));

        // Verify the service was called once
        Mockito.verify(productService, times(1)).updateProduct(Mockito.eq(productId), any(Product.class));
    }

    @Test
    public void testUpdateProduct_NotFound() throws Exception {
        Long productId = 2L;
        Product productDetails = new Product(productId, "Non-Existent Product", 1500.0);

        // Mock service to throw exception
        Mockito.when(productService.updateProduct(Mockito.eq(productId), any(Product.class)))
                .thenThrow(new RuntimeException("Product not found"));

        // Perform the PUT request
        mockMvc.perform(MockMvcRequestBuilders.put("/products/" + productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(productDetails)))
                .andExpect(status().isNotFound());

        // Verify the service was called once
        Mockito.verify(productService, times(1)).updateProduct(Mockito.eq(productId), any(Product.class));
    }
}
