package com.mylearning.springbootmockmvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mylearning.springbootmockmvc.controller.ProductController;
import com.mylearning.springbootmockmvc.entity.Product;
import com.mylearning.springbootmockmvc.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringBootMockMvcApplicationTests {

    @Autowired
    private ProductController productController;

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
}
