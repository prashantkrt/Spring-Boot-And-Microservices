package com.mylearning;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AssertThrowsCalculatorTest {
//    assertThrows(Class<T> expectedType, Executable executable);
//    @FunctionalInterface
//    public interface Executable {
//        void execute() throws Throwable;
//    }
    @Test
    void testDivideByZero() {
        Calculator2 calculator = new Calculator2();

        // will pass if the exception thrown and matches the exception type
        assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));

        // further if we wanted to check the message and compare it,
        // Assert that an ArithmeticException is thrown when dividing by zero
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> {
            calculator.divide(10, 0);
        });

        // Check if the exception message is correct
        assertEquals("Cannot divide by zero", exception.getMessage());
    }

    @Test
    void testDivideByZeroTryCatch() {
        Calculator2 calculator = new Calculator2();

        try {
            // Attempt to divide by zero, which should throw an exception
            calculator.divide(10, 0);
            fail("Expected ArithmeticException to be thrown");
        } catch (ArithmeticException e) {
            // If exception is caught, check if the correct message is returned
            assertEquals("Cannot divide by zero", e.getMessage());
        }
    }
}
