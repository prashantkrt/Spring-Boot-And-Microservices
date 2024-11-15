package com.mylearning;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // One instance of CalculatorTests for all tests
class CalculatorTests {

    private Calculator calculator;

    @BeforeAll
    void setUp() {
        calculator = new Calculator(); // One instance of Calculator used across all tests
    }

    // Nested class for testing addition
    @Nested
    class AddTests {

        @Test
        void testAddition() {
            int result = calculator.add(2, 3);
            assertEquals(5, result);
        }

        @Test
        void testAdditionWithNegative() {
            int result = calculator.add(-2, 3);
            assertEquals(1, result);
        }
    }

    // Nested class for testing subtraction
    @Nested
    class SubtractTests {

        @Test
        void testSubtraction() {
            int result = calculator.subtract(5, 3);
            assertEquals(2, result);
        }

        @Test
        void testSubtractionWithNegative() {
            int result = calculator.subtract(2, 5);
            assertEquals(-3, result);
        }
    }

    // Nested class for testing multiplication
    @Nested
    class MultiplyTests {

        @Test
        void testMultiplication() {
            int result = calculator.multiply(2, 3);
            assertEquals(6, result);
        }

        @Test
        void testMultiplicationWithZero() {
            int result = calculator.multiply(2, 0);
            assertEquals(0, result);
        }
    }

    // Nested class for testing division
    @Nested
    class DivideTests {

        @Test
        void testDivision() {
            int result = calculator.divide(6, 2);
            assertEquals(3, result);
        }

        @Test
        void testDivisionByZero() {
            try {
                calculator.divide(10, 0);
            } catch (ArithmeticException e) {
                assertEquals("Cannot divide by zero", e.getMessage());
            }
        }
    }
}
