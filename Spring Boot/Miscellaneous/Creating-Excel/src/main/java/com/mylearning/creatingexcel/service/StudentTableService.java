package com.mylearning.creatingexcel.service;


import com.mylearning.creatingexcel.helper.Helper;
import com.mylearning.creatingexcel.model.StudentTable;
import com.mylearning.creatingexcel.repository.StudentTableRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public class StudentTableService {

    @Autowired
    private StudentTableRepository studentTableRepository;

//    Already loaded to persistence db mysql locally
//    @PostConstruct
//    public void init() {
//        System.out.println(studentTableRepository.findAll());
//    }


    public ByteArrayInputStream getStudentTableData() {
        List<StudentTable> studentTableList = studentTableRepository.findAll();
        return Helper.dataToExcel(studentTableList);
    }
}
