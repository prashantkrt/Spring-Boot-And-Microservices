package com.mylearning;

class Student {
    private String name;
    private int[] grades;

    public Student(String name, int[] grades) {
        this.name = name;
        this.grades = grades;
    }

    public int[] getGrades() {
        return grades;
    }
}
