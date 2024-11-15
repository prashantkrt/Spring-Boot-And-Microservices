package com.mylearning;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
//Purpose: The @AfterEach annotation is used to mark a method that should run after each test method.
//It is typically used to clean up or reset resources that were used during the test.
public class AfterEachExample {
    @AfterEach
    void tearDownAfterEach() {
        System.out.println("After Each Test - This runs after each test method");
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
//Test 1
//After Each Test - This runs after each test method
//Test 2
//After Each Test - This runs after each test method