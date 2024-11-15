package com.mylearning;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
//By default, JUnit creates a new instance of the test class for each test.
//However, with @TestInstance, you can change the test lifecycle
//so that a single instance is used for all test methods in the test class.


//Controls whether a new instance of the test class is created for each test.


//@TestInstance(TestInstance.Lifecycle.PER_METHOD) // This is the default and can be omitted
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestInstanceExample {

    public TestInstanceExample() {
        System.out.println("Constructor called ");
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
