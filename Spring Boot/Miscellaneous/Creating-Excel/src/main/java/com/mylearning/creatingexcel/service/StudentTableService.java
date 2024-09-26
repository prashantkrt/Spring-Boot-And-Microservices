package com.mylearning.creatingexcel.service;


import com.mylearning.creatingexcel.repository.StudentTableRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentTableService {

    @Autowired
    private StudentTableRepository studentTableRepository;

    @PostConstruct
    public void init() {
        System.out.println(studentTableRepository.findAll());
    }
}
