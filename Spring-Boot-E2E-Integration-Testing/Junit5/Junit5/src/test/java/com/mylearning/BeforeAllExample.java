package com.mylearning;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

//Purpose: The @BeforeAll annotation is used to mark a method that should run once before all the tests in the test class.
//Important: The method must be static in JUnit 5.

public class BeforeAllExample {
    @BeforeAll
    static void setUpBeforeAll() {
        System.out.println("Before All Tests - This runs only once before all tests");
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

//Before All Tests - This runs only once before all tests
//Test 1
//Test 2
