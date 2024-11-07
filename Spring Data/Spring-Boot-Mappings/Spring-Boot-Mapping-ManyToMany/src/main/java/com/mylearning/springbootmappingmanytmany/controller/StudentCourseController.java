package com.mylearning.springbootmappingmanytmany.controller;

import com.mylearning.springbootmappingmanytmany.entity.Course;
import com.mylearning.springbootmappingmanytmany.entity.Student;
import com.mylearning.springbootmappingmanytmany.repository.CourseRepository;
import com.mylearning.springbootmappingmanytmany.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class StudentCourseController {

    private StudentRepository studentRepository;

    private CourseRepository courseRepository;

    public StudentCourseController(StudentRepository studentRepository,
                                   CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @PostMapping
    public Student saveStudentWithCourse(@RequestBody Student student){
        return  studentRepository.save(student);
    }

    @GetMapping
    public List<Student> findALlStudents(){
        return studentRepository.findAll();
    }

    @GetMapping("/{studentId}")
    public Student findStudent(@PathVariable Integer studentId){
        return studentRepository.findById(studentId).orElse(null);
    }

    @GetMapping("/fetch/courses")
    public List<Course> findAllCourses(){
        return courseRepository.findAll();
    }

    @GetMapping("/fetch/student/courses/{id}")
    public List<Course> findCoursesByStudentId(@PathVariable Integer id){
       Student student = studentRepository.findById(id).orElse(null);
        return student.getCourses();
    }


    @GetMapping("/fetch/course/students/{id}")
    public List<Student> findStudentsByCourseId(@PathVariable Integer id){
       Course course = courseRepository.findById(id).orElse(null);
        return course.getStudents();
    }

}
