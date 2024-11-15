package com.mylearning;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DisplayNameExample {

    @Test
    @DisplayName("Test for adding two positive numbers")
    void testAddPositiveNumbers() {
        int result = 2 + 3;
        assertEquals(5, result);
    }

    @Test
    @DisplayName("Test for subtracting numbers")
    void testSubtractNumbers() {
        int result = 5 - 3;
        assertEquals(2, result);
    }

    @Test
    @DisplayName("Test for multiplying numbers")
    void testMultiplyNumbers() {
        int result = 4 * 2;
        assertEquals(8, result);
    }


    // You can use both annotations together to disable a test while providing a descriptive name
    // and reason for its disabled state.
    @Test
    @DisplayName("Test for dividing by zero")
    @Disabled("Currently failing due to division by zero error")
    void testDivideByZero() {
        int result = 10 / 0;  // This would throw an ArithmeticException if executed
        assertEquals(0, result);
    }

    @Test
    @DisplayName("Test for multiplying two numbers")
    void testMultiplyNumbers2() {
        int result = 5 * 3;
        assertEquals(15, result);
    }
}

//Test for adding two positive numbers
//Test for subtracting numbers
//Test for multiplying numbers
//Test for dividing by zero  - Disabled: Currently failing due to division by zero error
//Test for multiplying two numbers
