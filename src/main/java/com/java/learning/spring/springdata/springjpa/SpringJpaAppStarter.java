package com.java.learning.spring.springdata.springjpa;

import com.java.learning.spring.springdata.springjpa.model.Employee;
import com.java.learning.spring.springdata.springjpa.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SpringJpaAppStarter implements CommandLineRunner{

    @Autowired
    private EmployeeService employeeService;

    public static void main(String... args){
        SpringApplication.run(SpringJpaAppStarter.class, args);
    }

    @Override
    public void run(String... args) {
        employeeService.saveAllEmployee(createAndEmployees());

        List<Employee> employeeList = employeeService.getAllEmployees();

        Employee employee = employeeList.stream().filter(e -> e.getId().equals("1")).findFirst().get();

        Set<Employee> reportees = employeeService.getAllReportees(employee);

        reportees.stream().sorted((emp1, emp2)-> emp1.getId().compareTo(emp2.getId())).forEach(System.out::println);
        System.out.println("Completed execution of Application!!");
    }

    private List<Employee> createAndEmployees(){
        return new ArrayList<Employee>(){{
            add(new Employee("1", "Paresh", ""));
            add(new Employee("2", "Faheem", "1"));
            add(new Employee("3", "Vivek Korani", "2"));
            add(new Employee("4", "Vivek Savita", "2"));
            add(new Employee("5", "Arun", "2"));
            add(new Employee("6", "Vinay", "2"));
            add(new Employee("7", "Debanka", "5"));
            add(new Employee("8", "Prashansa", "6"));
            add(new Employee("9", "Priyank", "3"));
        }};
    }
}