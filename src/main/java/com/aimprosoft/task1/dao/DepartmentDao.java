package com.aimprosoft.task1.dao;

import com.aimprosoft.task1.model.Department;

import java.sql.SQLException;

public interface DepartmentDao {
    Iterable<Department> list() throws SQLException;

    Department getByName(String name) throws SQLException;

    void add(Department department) throws SQLException;

    void delete(String name) throws SQLException;

    void update(String name, Department department) throws SQLException;
}
