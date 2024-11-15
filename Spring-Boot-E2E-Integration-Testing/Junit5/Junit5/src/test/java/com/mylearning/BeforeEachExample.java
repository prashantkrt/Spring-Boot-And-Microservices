package com.mylearning;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
// Purpose: The @BeforeEach annotation is used to mark a method that should run before
// each test method. It is useful for common setup for every individual test.
public class BeforeEachExample {
    @BeforeEach
    void setUpBeforeEach() {
        System.out.println("Before Each Test - This runs before each test method");
    }

    @Test
    void test1() {
        System.out.println("Test 1");
    }

    @Test
    void test2() {
        System.out.println("Test 2");
    }
}
//Before Each Test - This runs before each test method
//Test 1
//Before Each Test - This runs before each test method
//Test 2