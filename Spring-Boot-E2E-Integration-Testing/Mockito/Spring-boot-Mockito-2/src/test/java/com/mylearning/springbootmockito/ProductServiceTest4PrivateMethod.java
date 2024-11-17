package com.mylearning.springbootmockito;

import com.mylearning.springbootmockito.service.ProductService;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// @ExtendWith(PowerMockExtension.class) using powermock
public class ProductServiceTest4PrivateMethod {


    private ProductService productService;

    @Test
    public void testPrivateValidateMethod() throws Exception {
        // Create an instance of ProductService
        ProductService productService = new ProductService();

        // Access the private method using reflection
        // params ⇒ getDeclaredMethod("methodName", parameters it need)
        Method validateMethod = ProductService.class.getDeclaredMethod("validate", Long.class);
        validateMethod.setAccessible(true); // Make it accessible

        // Invoke the private method and test its behavior
        //params ⇒ invoke(obj (The instance of the class on which you want to call the method) , args (Object...):The arguments to pass to the method when invoking it.)
        boolean result1 = (boolean) validateMethod.invoke(productService, 10L);
        assertTrue(result1, "Expected true for valid ID");

        boolean result2 = (boolean) validateMethod.invoke(productService, -1L);
        assertFalse(result2, "Expected false for invalid ID");
    }


//    approach2
//    @Test
//    public void testPrivateValidateMethodWithPowerMockito() throws Exception {
//        // Spy on the ProductService class
//        ProductService productService = PowerMockito.spy(new ProductService());
//
//        // Use PowerMockito to invoke the private method
//        boolean result1 = PowerMockito.invokeMethod(productService, "validate", 10L);
//        assertTrue(result1, "Expected true for valid ID");
//
//        boolean result2 = PowerMockito.invokeMethod(productService, "validate", -1L);
//        assertFalse(result2, "Expected false for invalid ID");
//    }

}
