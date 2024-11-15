package com.mylearning;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class AssertionArrayStudentTest {

    @Test
    void testStudentGrades() {
        // Create a Student object with some grades
        int[] grades = {90, 80, 85, 70};
        Student student = new Student("John", grades);

        // Expected grades array
        int[] expectedGrades = {90, 80, 85, 70};

        // Assert that the student's grades match the expected array
        assertArrayEquals(expectedGrades, student.getGrades(), "Grades should match");
    }

    @Test
    void testStudentGradesFailure() {
        // Create a Student object with some grades
        int[] grades = {90, 80, 85, 70};
        Student student = new Student("John", grades);

        // Expected grades array (intentionally different)
        int[] expectedGrades = {90, 80, 85, 75};

        // Assert that the student's grades match the expected array
        // This will fail because the last element is different (70 vs 75)
        assertArrayEquals(expectedGrades, student.getGrades(), "Grades should match");
    }

    @Test
    void testStudentNames() {
        String[] names = {"John", "Alice", "Bob"};
        String[] expectedNames = {"John", "Alice", "Bob"};

        // Assert that the names array matches the expected array
        assertArrayEquals(expectedNames, names, "Names should match");
    }

}