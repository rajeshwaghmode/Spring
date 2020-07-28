package com.java.learning.spring.springdata.springjpa.repository;

import com.java.learning.spring.springdata.springjpa.model.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, String> {

    @Override
    <S extends Department> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    List<Department>  findAll();
}
