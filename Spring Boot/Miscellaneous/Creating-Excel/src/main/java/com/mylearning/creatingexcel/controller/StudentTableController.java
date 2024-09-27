package com.mylearning.creatingexcel.controller;

import com.mylearning.creatingexcel.service.StudentTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/")
public class StudentTableController {

    @Autowired
    private StudentTableService studentTableService;

    @RequestMapping(value = "/excel", method = RequestMethod.GET)
    public ResponseEntity<Resource> downLoadExcel() {
        ByteArrayInputStream byteArrayInputStream = studentTableService.getStudentTableData();
        InputStreamResource resource = new InputStreamResource(byteArrayInputStream);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=student.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(resource);
    }

}

