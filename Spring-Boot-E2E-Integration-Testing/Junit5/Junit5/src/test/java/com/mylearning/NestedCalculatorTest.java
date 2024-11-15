package com.mylearning;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
//The advantage of using nested test classes is that they allow you to group related tests together
class NestedCalculatorTest {

    // Outer test class setup
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    // Nested test class for addition
    @Nested
    class AddTests {

        @Test
        void testAddition() {
            int result = calculator.add(2, 3);
            assertEquals(5, result, "Addition result should be 5");
        }

        @Test
        void testAdditionWithNegative() {
            int result = calculator.add(-1, -3);
            assertEquals(-4, result, "Addition result should be -4");
        }
    }

    // Nested test class for subtraction
    @Nested
    class SubtractTests {

        @Test
        void testSubtraction() {
            int result = calculator.subtract(5, 3);
            assertEquals(2, result, "Subtraction result should be 2");
        }

        @Test
        void testSubtractionWithNegative() {
            int result = calculator.subtract(-3, 2);
            assertEquals(-5, result, "Subtraction result should be -5");
        }
    }

    // Nested test class for multiplication
    @Nested
    class MultiplyTests {

        @Test
        void testMultiplication() {
            int result = calculator.multiply(2, 3);
            assertEquals(6, result, "Multiplication result should be 6");
        }

        @Test
        void testMultiplicationWithZero() {
            int result = calculator.multiply(0, 5);
            assertEquals(0, result, "Multiplication result should be 0");
        }
    }
}
