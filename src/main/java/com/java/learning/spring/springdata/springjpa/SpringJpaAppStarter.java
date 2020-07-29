package com.java.learning.spring.springdata.springjpa;

import com.java.learning.spring.springdata.springjpa.model.Department;
import com.java.learning.spring.springdata.springjpa.model.Employee;
import com.java.learning.spring.springdata.springjpa.service.DepartmentService;
import com.java.learning.spring.springdata.springjpa.service.EmployeeService;
import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootApplication
public class SpringJpaAppStarter implements CommandLineRunner{

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    public static void main(String... args){
        SpringApplication.run(SpringJpaAppStarter.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Cache statistics at the start:");
        CacheManager cacheManager = CacheManager.ALL_CACHE_MANAGERS.get(0);
        Arrays.stream(cacheManager.getCacheNames())
                .collect(Collectors.toMap(Function.identity(), cacheName -> cacheManager.getCache(cacheName).getSize()))
                .forEach((k, v) -> System.out.println(k+ ":"+ v.intValue()));

        employeeService.saveAllEmployee(createAndGetEmployees());

        List<Employee> employeeList = employeeService.getAllEmployees();

        Employee employee = employeeList.stream().filter(e -> e.getId().equals("1")).findFirst().get();

        Set<Employee> reportees = employeeService.getAllReportees(employee);

        reportees.stream().sorted((emp1, emp2)-> emp1.getId().compareTo(emp2.getId())).forEach(System.out::println);

        System.out.println("Cache statistics at the end:");
        Arrays.stream(cacheManager.getCacheNames())
                .collect(Collectors.toMap(Function.identity(), cacheName -> cacheManager.getCache(cacheName).getSize()))
                .forEach((k, v) -> System.out.println(k+ ":"+ v.intValue()));
        System.out.println("Completed execution of Application!!");
    }

    private List<Employee> createAndGetEmployees(){
        List<Department> departments = createAndGetDepartments();
            Employee e1 = new Employee("1", "Paresh", ""); e1.setDepartment(departments.get(0));
            Employee e2 = new Employee("2", "Faheem", "1"); e2.setDepartment(departments.get(1));
            Employee e3 = new Employee("3", "Korani", "2"); e3.setDepartment(departments.get(2));
            Employee e4 = new Employee("4", "Savita", "2"); e4.setDepartment(departments.get(3));
            Employee e5 = new Employee("5", "Arun", "2"); e5.setDepartment(departments.get(0));
            Employee e6 = new Employee("6", "Vinay", "2"); e6.setDepartment(departments.get(1));
            Employee e7 = new Employee("7", "Debanka", "5"); e7.setDepartment(departments.get(2));
            Employee e8 = new Employee("8", "Prashansa", "6"); e8.setDepartment(departments.get(3));
            Employee e9 = new Employee("9", "Priyank", "3"); e9.setDepartment(departments.get(0));

        ArrayList<Employee> employees = new ArrayList<Employee>(){{
            add(e1);
            add(e2);
            add(e3);
            add(e4);
            add(e5);
            add(e6);
            add(e7);
            add(e8);
            add(e9);
        }};

        return employees;
    }

    private List<Department> createAndGetDepartments(){
        Department d1 = new Department(); d1.setId(101L);d1.setName("Investment Banking");
        Department d2 = new Department(); d2.setId(102L);d2.setName("Wealth Management and Assets");
        Department d3 = new Department(); d3.setId(103L);d3.setName("Corporate Banking");
        Department d4 = new Department(); d4.setId(104L);d4.setName("Human Resource");

        List<Department> departments = new ArrayList<>();
        departments.add(d1);
        departments.add(d2);
        departments.add(d3);
        departments.add(d4);

        departmentService.saveAll(departments);

        return departmentService.findAll();
    }
}