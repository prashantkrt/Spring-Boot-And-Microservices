package com.mylearning;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

//Purpose: The @AfterAll annotation is used to mark a method that should run once after all the tests in the test class.
//Important: The method must be static in JUnit 5.

public class AfterAllExample {

    @AfterAll
    static void tearDownAfterAll() {
        System.out.println("After All Tests - This runs only once after all tests");
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
//Test 2
//After All Tests - This runs only once after all tests
