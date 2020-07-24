package com.java.learning.spring.springdata.springjpa.service;

import com.java.learning.spring.springdata.springjpa.model.Employee;
import com.java.learning.spring.springdata.springjpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service(value = "employeeService")
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public void saveEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    public void saveAllEmployee(List<Employee> employeeList){
        employeeRepository.saveAll(employeeList);
    }

    public List<Employee> getAllEmployees(){
        return (List<Employee>)employeeRepository.findAll();
    }

    public Set<Employee> getAllReportees(Employee employee) {
        return findReportees(employee, getAllEmployees());
    }

    private Set<Employee> findReportees(Employee employee, List<Employee> employeeList){
        if(employee == null) new HashSet<>();

        Set<Employee> directReportees = employeeList
                .parallelStream()
                .filter(emp -> !emp.equals(employee) && emp.getManagerId().isPresent() && emp.getManagerId().get().equals(employee.getId()))
                .collect(Collectors.toSet());
        Set<Employee> indirectReportees = new HashSet<>();
        directReportees.forEach(directReportee -> indirectReportees.addAll(findReportees(directReportee, employeeList)));

        directReportees.addAll(indirectReportees); // gives all reportees

        return directReportees;
    }
}
