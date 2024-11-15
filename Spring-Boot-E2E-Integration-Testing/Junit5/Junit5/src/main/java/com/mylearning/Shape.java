package com.mylearning;

public class Shape {
    // Rectangle class with width and height
    public static class Rectangle {
        private final double width;
        private final double height;

        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        public double getArea() {
            return width * height;
        }

        public double getPerimeter() {
            return 2 * (width + height);
        }

        public boolean isCircle() {
            return false;
        }
    }

    // Circle class with radius
    public static class Circle {
        private final double radius;

        public Circle(double radius) {
            this.radius = radius;
        }

        public double getArea() {
            return Math.PI * radius * radius;
        }

        public double getPerimeter() {
            return 2 * Math.PI * radius;
        }

        public boolean isCircle() {
            return true;
        }
    }
}
