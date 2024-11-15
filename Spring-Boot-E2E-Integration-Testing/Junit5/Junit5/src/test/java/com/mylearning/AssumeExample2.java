package com.mylearning;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assumptions;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssumeExample2 {
    @Test
    void testAssumeTrueWithoutMessage() {
        int value = 10;

        // The test will be skipped if value is not greater than 5 (assumption fails)
        Assumptions.assumeTrue(value > 5);

        // If assumption is true, the test will proceed
        assertEquals(10, value);
    }

    @Test
    void testAssumeFalseWithoutMessage() {
        int value = 0;

        // The test will be skipped if value is 0 (assumption fails)
        Assumptions.assumeFalse(value == 0);

        // If assumption is false, the test will proceed
        assertEquals(0, value);
    }
}
