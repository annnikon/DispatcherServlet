package com.aimprosoft.task1.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "departmentId")
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "info")
    private String info;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departments")
    private List<Employee> employees = new ArrayList<>();

    public Department() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
