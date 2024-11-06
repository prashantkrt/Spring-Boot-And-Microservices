package com.mylearning.springbootmappingonetoone;

import com.mylearning.springbootmappingonetoone.entity.Laptop;
import com.mylearning.springbootmappingonetoone.entity.Student;
import com.mylearning.springbootmappingonetoone.repository.LaptopRepository;
import com.mylearning.springbootmappingonetoone.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootMappingOneToOneApplication implements CommandLineRunner {

    @Autowired
    private LaptopRepository laptopRepository;

    @Autowired
    private StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMappingOneToOneApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        // In laptop table it is mandatory to add the mapped field before saving
        Laptop laptop = new Laptop();
        laptop.setLaptopId(2);
        laptop.setModelNumber("AZA123");
        laptop.setBrand("Samsung");

        // In the Student table it is not mandatory
        Student student = new Student();
        student.setStudentId(102);
        student.setStudentName("Ratan");
        student.setAbout("Topper Ladka Hero Heralal");

        //studentRepository.save(student); // will be only reflected the student table

        // after CascadeType.All even if we save Student, Laptop data will be automatically loaded to the database
        laptop.setStudent(student);
        student.setLaptop(laptop);
        studentRepository.save(student);


    }
}
