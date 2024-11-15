package com.mylearning;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AssertionShapeTest {
    // Test for Rectangle's area and perimeter
    @Test
    void testRectangle() {
        Shape.Rectangle rectangle = new Shape.Rectangle(4, 5);

        // Assert area: expected = 20
        assertEquals(20, rectangle.getArea(), "Area of rectangle should be 20");

        // Assert perimeter: expected = 18
        assertEquals(18, rectangle.getPerimeter(), "Perimeter of rectangle should be 18");
    }

    // Test for Circle's area and perimeter
    @Test
    void testCircle() {
        Shape.Circle circle = new Shape.Circle(3);

        //The delta parameter in assertEquals is used when you are comparing floating-point values (such as double or float).
        //assertEquals(expected, actual, delta, message);

        // Assert area: expected = Math.PI * 3^2 (about 28.274)
        assertEquals(28.274333882308138, circle.getArea(), 0.0001, "Area of circle should be close to 28.274");

        // Assert perimeter: expected = 2 * Math.PI * 3 (about 18.849)
        assertEquals(18.84955592153876, circle.getPerimeter(), 0.0001, "Perimeter of circle should be close to 18.849");
    }

    // Test if area of circle is not equal to rectangle area (assertNotEquals)
    // Using assertNotEquals, we check that the area of the Rectangle is not equal to the area of the Circle.
    @Test
    void testDifferentAreas() {
        Shape.Rectangle rectangle = new Shape.Rectangle(4, 5);
        Shape.Circle circle = new Shape.Circle(3);

        assertNotEquals(rectangle.getArea(), circle.getArea(), "Area of rectangle and circle should not be the same");
    }

    // Test if circle area is greater than rectangle area
    @Test
    void testCircleAreaGreaterThanRectangle() {
        Shape.Rectangle rectangle = new Shape.Rectangle(4, 5);
        Shape.Circle circle = new Shape.Circle(3);

        assertTrue(circle.getArea() > rectangle.getArea(), "Circle area should be greater than rectangle area");
    }

    // Test if the perimeter of a rectangle is less than a circle
    @Test
    void testPerimeterComparison() {
        Shape.Rectangle rectangle = new Shape.Rectangle(4, 5);
        Shape.Circle circle = new Shape.Circle(3);

        assertTrue(rectangle.getPerimeter() < circle.getPerimeter(), "Rectangle perimeter should be less than circle perimeter");
    }

    // Test for Circle perimeter using Supplier for lazy message evaluation
    @Test
    void testCirclePerimeterWithSupplier() {
        Shape.Circle circle = new Shape.Circle(3);

        // Using Supplier for error message
        assertEquals(18.84955592153876, circle.getPerimeter(), () -> "Perimeter of circle should be " + (2 * Math.PI * 3));
    }

//    assertFalse(rectangle.isCircle()) ensures that for a rectangle, the isCircle() method correctly returns false.
//    assertTrue(circle.isCircle()) checks that a circle returns true.


    //We use assertFalse to verify that the condition (rectangle.isCircle()) is false for rectangles.
    //If the condition is true when we expect it to be false, the test will fail.

    @Test
    void testIsCircleForRectangle() {
        // Create an instance of Shape (outer class)
        Shape shape = new Shape();

        // Create a Rectangle using the createRectangle method
        Shape.Rectangle rectangle = new Shape.Rectangle(3,4);

        // Assert that isCircle() returns false for a Rectangle
        assertFalse(rectangle.isCircle(), "Rectangle should not be a circle");
    }

    @Test
    void testIsCircleTrue() {
        // Create a circle shape
         Shape shape = new Shape();

         Shape.Circle circle = new Shape.Circle(3);
        // Assert that isCircle() returns true for a circle shape
        assertFalse(circle.isCircle(), "Shape should not be a circle");
    }

    /*
    assertEquals(3.14, 3.14159, 0.001, "Values should be close to each other");

    In this case:

    Expected value: 3.14
    Actual value: 3.14159
    Delta: 0.001
    The absolute difference between the expected and actual values is 0.00159, which is greater than the allowed delta (0.001), so the assertion would fail.
    * */
}
