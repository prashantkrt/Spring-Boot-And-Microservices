package com.mylearning;
import org.junit.jupiter.api.RepeatedTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.RepetitionInfo;

class RepeatedCalculatorTests {

    private final Calculator calculator = new Calculator();

    @RepeatedTest(5) // Repeats this test 5 times
    void testAddition() {
        int result = calculator.add(2, 3);
        assertEquals(5, result, "Addition result should always be 5");
    }

    @RepeatedTest(5) // Repeats this test 5 times
    void testAddition(RepetitionInfo repetitionInfo) {
        int result = calculator.add(2, 3);
        assertEquals(5, result, "Addition result should always be 5");

        // Accessing the repetition info
        System.out.println("Repetition " + repetitionInfo.getCurrentRepetition() + " of " + repetitionInfo.getTotalRepetitions());
        //getCurrentRepetition(): Returns the current repetition number (e.g., 1, 2, 3, etc.).
        // getTotalRepetitions(): Returns the total number of repetitions (e.g., 5 if the test is repeated 5 times).
    }

    @RepeatedTest(5)
    void testDynamicAssertion(RepetitionInfo repetitionInfo) {
        int result = calculator.add(2, 3);
        int currentRepetition = repetitionInfo.getCurrentRepetition();

        // Dynamic assertion based on current repetition
        if (currentRepetition == 1) {
            assertEquals(5, result, "Repetition 1: Addition result should always be 5");
        } else if (currentRepetition == 2) {
            // Simulating a different condition for repetition 2
            assertEquals(5, result, "Repetition 2: Addition result should always be 5");
        } else if (currentRepetition == 3) {
            // You can introduce a different assertion for repetition 3 if needed
            assertEquals(5, result, "Repetition 3: Addition result should always be 5");
        } else {
            // Any custom logic for other repetitions
            assertEquals(5, result, "Repetitions 4 and 5: Addition result should always be 5");
        }
    }
}
