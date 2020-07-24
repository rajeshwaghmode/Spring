package com.java.learning.spring.springdata.springjpa.repository;

import com.java.learning.spring.springdata.springjpa.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {

}
