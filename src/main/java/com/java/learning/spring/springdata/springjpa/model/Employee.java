package com.java.learning.spring.springdata.springjpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Optional;

@Entity
public class Employee {
    @Id
    private String id;

    @Column
    private String name;
    private String managerId;

    public Employee(){}

    public Employee(String id, String name, String managerId){
        this.id = id;
        this.name = name;
        this.managerId = managerId;
    }
    public String getId() {
        return id;
    }

    public Optional<String> getName() {
        return Optional.of(name);
    }

    public Optional<String> getManagerId() {
        return Optional.of(managerId);
    }

    @Override
    public int hashCode() {
        return 20 * this.managerId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if(obj instanceof Employee)
            return false;

        if(this == obj) return true;

        Employee emp = (Employee) obj;

        return this.id.equalsIgnoreCase(emp.getId());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name=" + name +
                '}';
    }
}
