package com.mylearning;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assumptions;

import java.time.LocalTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

//assumptions refer to conditions or prerequisites that must be met for a test to run.
//If those assumptions are not met, the test will be skipped or ignored.

public class AssumptionExample {

    //Assumptions.assumeTrue(condition, message)
    @Test
    void testAssumeTrueWithPositiveNumber() {
        // Assume the test should only run if the number is positive
        int number = 10;

        // The test will be skipped if the number is not positive
        Assumptions.assumeTrue(number > 0, "Skipping test because number is not positive");

        // The test will run only if the assumption is true (number > 0)
        assertTrue(number > 0);
    }

    @Test
    void testAssumeTrueWithNonEmptyString() {
        // Assume the test should only run if the string is not empty
        String name = "John";

        // The test will be skipped if the string is empty
        Assumptions.assumeTrue(!name.isEmpty(), "Skipping test because the string is empty");

        // The test will run only if the assumption is true (string is not empty)
        assertFalse(name.isEmpty());
    }

    @Test
    void testOnlyOnWindows() {
        // Assumption: This test will run only if the OS is Windows
        Assumptions.assumeTrue(System.getProperty("os.name").contains("Windows"));

        // This will only execute if the assumption is true
        assertEquals(5, 2 + 3);
    }

    @Test
    void testAssumeTrueWithTimeCheck() {
        // Get the current hour
        int currentHour = LocalTime.now().getHour();

        // Assume the test should only run if the current time is between 9 AM and 5 PM
        Assumptions.assumeTrue(currentHour >= 9 && currentHour <= 17, "Skipping test because it's outside working hours");

        // The test will run only if the assumption is true (time is between 9 AM and 5 PM)
        assertTrue(currentHour >= 9 && currentHour <= 17);
    }

    @Test
    void testAssumptionFailure() {
        // Assumption: This test will be skipped if the OS is not Windows
        Assumptions.assumeTrue(System.getProperty("os.name").contains("Windows"));

        // This will only execute if the assumption is true
        assertEquals(5, 2 + 3);
    }

    @Test
    void testAssumeFalse() {
        // Assumption: This test will run only if the OS is NOT Windows
        Assumptions.assumeFalse(System.getProperty("os.name").contains("Windows"));

        // This will only execute if the assumption is false
        assertEquals(5, 2 + 3);
    }

    @Test
    void testAssumeFalse2() {
        // Assume the test will only run if the number is not 0
        int number = 5;

        // This assumption will skip the test if the number is 0
        Assumptions.assumeFalse(number == 0, "Skipping test because number is 0");

        // The actual test will run only if the assumption is false
        assertEquals(5, number);
    }

//    @Test
//    void testAssumeThat() {
//        // Let's say you want to skip the test if the length of the string is less than 5
//        String testString = "Hello";
//
//        // Using Hamcrest Matcher with Assumptions.assumeThat
//        Assumptions.assumeThat(testString.length(), greaterThanOrEqualTo(5)); // Skip if the length is less than 5
//
//        // The following assertion will run only if the assumption passes
//        assertThat(testString, containsString("He"));
//    }
//
//    @Test
//    void testAssumeThatFail() {
//        // This test will be skipped since the assumption is false
//        String testString = "Hi";
//
//        // This assumption fails because the string length is less than 5
//        Assumptions.assumeThat(testString.length(), greaterThanOrEqualTo(5)); // Skip if the length is less than 5
//
//        // This assertion won't run because the test will be skipped
//        assertThat(testString, containsString("H"));
//    }
}
