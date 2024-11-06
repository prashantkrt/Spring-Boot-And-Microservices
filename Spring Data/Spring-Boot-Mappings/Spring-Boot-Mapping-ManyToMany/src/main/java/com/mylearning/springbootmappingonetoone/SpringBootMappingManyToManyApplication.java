package com.mylearning.springbootmappingonetoone;

import com.mylearning.springbootmappingonetoone.experimentRepository.CategoryRepository;
import com.mylearning.springbootmappingonetoone.experimentRepository.ProductRepository;
import com.mylearning.springbootmappingonetoone.repository.CourseRepository;
import com.mylearning.springbootmappingonetoone.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootMappingManyToManyApplication implements CommandLineRunner {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMappingManyToManyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        fetchDetails();
    }


    public void fetchDetails() {
        //Bi-Directional

    }
}
