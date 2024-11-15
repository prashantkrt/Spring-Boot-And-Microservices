package com.mylearning;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

// The @Disabled annotation is used to temporarily disable a test method.
// This is useful when you want to skip certain tests.
public class DisabledExample {
    @Test
    void test1() {
        System.out.println("Test 1");
    }

    //skip this
    @Test
    @Disabled("This test is temporarily disabled")
    void test2() {
        System.out.println("Test 2");
    }

    //skip this
    @Test
    @Disabled
    void test3() {
        System.out.println("Test 2");
    }
}

//Test 1
//This test is temporarily disabledThis test is temporarily disabled
//void com.mylearning.DisabledExample.test3() is @Disabled

