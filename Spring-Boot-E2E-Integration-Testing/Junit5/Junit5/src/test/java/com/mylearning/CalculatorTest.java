package com.mylearning;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
// Test Driven Development
// Write test then code
public class CalculatorTest {

    //assertEquals(expected, actual);

    //Junit4
    //assertEquals(message, expected, actual); Optionally, you can include a custom message:

    //Junit5
    //assertEquals(expected, actual, message);
    @Test
    void testAdd() {
        Calculator calculator = new Calculator();
        int result = calculator.add(2, 3);
        assertEquals(5, result, "Addition result should be 5");
    }

    @Test
    void testSubtract() {
        Calculator calculator = new Calculator();
        int result = calculator.subtract(5, 3);
        assertEquals(2, result, "Subtraction result should be 2");
    }

    @Test
    void testMultiply() {
        Calculator calculator = new Calculator();
        int result = calculator.multiply(4, 3);
        assertEquals(12, result, "Multiplication result should be 12");
    }

    @Test
    void testDivide() {
        Calculator calculator = new Calculator();
        int result = calculator.divide(10, 2);
        assertEquals(5, result, "Division result should be 5");
    }

//    In JUnit 5, the third parameter of assertEquals can either be:
//    A simple String (for the error message), or
//    A Supplier<String> (for lazy evaluation of the error message).
//    You don’t need to use a Supplier<String> if you just want to pass a simple string message.

    @Test
    void testAddition() {
        Calculator calculator = new Calculator();
        int result = calculator.add(2, 3);

        // Using a Supplier for the message
        assertEquals(5, result, () -> "Addition result should be " + 5);


        // will only execute it the test case is failing if we use supplier
        // If you want to lazily evaluate the message (only when the assertion fails), you use a Supplier<String> like this:
        // Using Supplier for an error message
        assertEquals(5, result, () -> "Expected 5 but got " + result);
    }
}
