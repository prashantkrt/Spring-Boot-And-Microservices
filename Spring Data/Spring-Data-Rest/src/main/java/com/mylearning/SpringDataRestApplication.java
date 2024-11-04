package com.mylearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataRestApplication.class, args);
    }

}

/*
*
*    http://localhost:8080/ =>
*   {
*        "_links": {
*        "employees": {
*        "href": "http://localhost:8080/employees{?page,size,sort*}",
*        "templated": true
*        },
*        "students": {
*        "href": "http://localhost:8080/students{?page,size,sort*}",
*        "templated": true
*        },
*        "profile": {
*        "href": "http://localhost:8080/profile"
*       }
*      }
*   }
*    We can simply perform the crud operation without controller and service classes
*    http://localhost:8080/employees   RequestBody => post
*    http://localhost:8080/employees/1  => get
*    http://localhost:8080/employees/2  => delete
*    http://localhost:8080/employees/2  RequestBody => put
*
*    http://localhost:8080/employees?page=0&size=3&sort=name  // offset / limit / fieldName for sorting
*
* */
