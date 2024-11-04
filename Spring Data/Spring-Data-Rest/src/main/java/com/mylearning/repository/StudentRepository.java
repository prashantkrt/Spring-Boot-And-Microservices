package com.mylearning.repository;

import com.mylearning.entity.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// customizing the
//collectionResourceRel is the name that will appear as the relation in the JSON representation of your collection.
//"student-api": [ // collectionResourceRel
//      {
//        "id": 1,
//        "name": "John Doe",
//        "age": 21,
//        "_links": {
//          "self": {
//            "href": "http://localhost:8080/student-api/1"
//          },
//          "student-api": {
//            "href": "http://localhost:8080/student-api"
//          }
// path defines the URL path under which this repository’s resources will be exposed.
// http://localhost:8080/student-api
@RepositoryRestResource(collectionResourceRel = "student-api",path = "student-api")
public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByName(String lastName);
    //http://localhost:8080/student-api/search/findByName?name=Prashant
}
