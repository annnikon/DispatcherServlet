package com.aimprosoft.task1.dao;

import com.aimprosoft.task1.model.Employee;

import java.sql.SQLException;

public interface EmployeeDao {

    Iterable<Employee> list(String departmentName) throws SQLException;

    Employee getByEmail(String email) throws SQLException;

    void add(Employee employee) throws SQLException;

    void delete(String email) throws SQLException;

    void update(String email, Employee employee) throws SQLException;
}
