package com.aimprosoft.task1.handler.employees;

import com.aimprosoft.task1.dao.EmployeeDao;
import com.aimprosoft.task1.model.Employee;
import com.aimprosoft.task1.servlet.ViewNames;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class PostAddHandler extends AbstractEmployeesHandler {
    private static EmployeeDao dao = getEmployeeDao();

    @Override
    public void parseParams(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String newEmail = request.getParameter("newEmail");
        String departmentName = request.getParameter("departmentName");
        String newDepartmentName = request.getParameter("newDepartmentName");
        request.setAttribute("departmentName", departmentName);

        if (!validateEmployee(request)) {
            request.setAttribute("warning", "Please fill correctly all the fields:");
            showAgainEmployee(request, response);
            return;
        }
        if (!canAddEmployee(newEmail)) {
            request.setAttribute("warning", "This email already exists: " + newEmail);
            showAgainEmployee(request, response);
            return;
        }

        try {
            Employee employee = readEmployee(request);
            dao.add(employee);
            request.setAttribute("status", "Added employee " + employee.getEmail());
            request.setAttribute("departmentName", employee.getDepartmentName());
            request.setAttribute("employees", dao.list(departmentName));

        } catch (SQLException e) {
            request.setAttribute("warning", "No such department: " + newDepartmentName);
            showAgainEmployee(request, response);

        } catch (Exception e) {
            request.setAttribute("status", "Employee cannot be added because: " + e.getMessage());

        }
        setViewName(ViewNames.EMPLOYEES);
    }


}
