package com.aimprosoft.task1.model;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

//@Entity
@Table(name = "employees")
public class Employee {

    //for Hibernate only
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeId")
    private long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "room")
    private int room;

    @Column(name = "department")
    private String departmentName;

    //for Hibernate only
    @ManyToOne
    @JoinColumn(name = "departmentId")
    private Department department;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", room=" + room +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
