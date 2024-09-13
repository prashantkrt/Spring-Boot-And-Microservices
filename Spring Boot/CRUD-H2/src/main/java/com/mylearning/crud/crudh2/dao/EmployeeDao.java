package com.mylearning.crud.crudh2.dao;

import com.mylearning.crud.crudh2.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDao extends CrudRepository<Employee, Long> {
}
