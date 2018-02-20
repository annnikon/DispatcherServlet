package com.aimprosoft.task1.dao;

import com.aimprosoft.task1.model.Employee;
import com.aimprosoft.task1.utils.MysqlUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class EmployeeDaoImpl implements EmployeeDao {
    Connection connection;
    PreparedStatement preparedStatement;

      public Iterable<Employee> list(String departmentName) throws SQLException {
        HashSet<Employee> list = new HashSet<>();
        String sql = "SELECT email, name, birthday, room, department from employees" +
                " where department = ? ;";
          try { connection = MysqlUtils.getConnection();
              preparedStatement = connection.prepareStatement(sql);

              preparedStatement.setString(1, departmentName);
              ResultSet resultSet = preparedStatement.executeQuery();
                  if (resultSet != null) {

                      while (resultSet.next()) {

                          Employee employee = new Employee();
                          employee.setEmail(resultSet.getString("email"));
                          employee.setName(resultSet.getString("name"));
                          employee.setBirthday(resultSet.getDate("birthday"));
                          employee.setRoom(resultSet.getInt("room"));
                          employee.setDepartmentName(departmentName);
                          list.add(employee);

                      }
                  }
          }
          finally {
              MysqlUtils.close(preparedStatement);
          }

        return list;

    }

    public void add(Employee employee) throws SQLException {


        String sql = "INSERT INTO employees (email, name, birthday, room, department) VALUES"
                + " (?, ?, ?, ?, ?);";
        try { connection = MysqlUtils.getConnection();
              preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, employee.getEmail());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setDate(3,
                    new java.sql.Date(employee.getBirthday().getTime()));
            preparedStatement.setInt(4, employee.getRoom());
            preparedStatement.setString(5, employee.getDepartmentName());
            preparedStatement.executeUpdate();
        }
        finally {
            MysqlUtils.close(preparedStatement);
        }




    }

    public Employee getByEmail(String email) throws SQLException {

        Employee employee = null;
        String sql = "SELECT email, name, birthday, room, department from employees where email = ? ;";
        try { connection = MysqlUtils.getConnection();
             preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
          ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {

                if (resultSet.next()) {
                    employee = new Employee();
                    employee.setEmail(email);
                    employee.setName(resultSet.getString("name"));
                    employee.setBirthday(resultSet.getDate("birthday"));
                    employee.setRoom(resultSet.getInt("room"));
                    employee.setDepartmentName(resultSet.getString("department"));
                }


            }
        }
        finally {
            MysqlUtils.close(preparedStatement);
        }

        return employee;

    }

    public void update(String email, Employee newEmployee) throws SQLException {

        String sql = "UPDATE employees SET" +
                " email = ?, name = ?," +
                " birthday = ?, room = ?, " +
                "department = ? WHERE email = ? ;";
        try {connection = MysqlUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newEmployee.getEmail());
            preparedStatement.setString(2, newEmployee.getName());
            java.sql.Date currentDate = new java.sql.Date(newEmployee.getBirthday().getTime());
            preparedStatement.setString(3, currentDate.toString());
            preparedStatement.setInt(4, newEmployee.getRoom());
            preparedStatement.setString(5, newEmployee.getDepartmentName());
            preparedStatement.setString(6, email);
            preparedStatement.executeUpdate();

        }
        finally {
            MysqlUtils.close(preparedStatement);
        }


    }

    public void delete(String email) throws SQLException {

        String sql = "DELETE from employees where email = ? ;";

        try {
            connection = MysqlUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();
        }
        finally {
                MysqlUtils.close(preparedStatement);
            }

    }


}
