package com.java.learning.spring.springdata.springjpa.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Department {
    @Id
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return 20*3 + (this.id.hashCode());
    }

    @Override
    public boolean equals(Object obj) {

        if(obj instanceof Department)
            return false;

        if(this == obj) return true;

        Department dept = (Department) obj;

        return this.id.equals(dept.getId());
    }

    @Override
    public String toString() {
        return this.name;
    }

}
