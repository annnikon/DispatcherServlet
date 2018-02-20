package com.aimprosoft.task1.handler.employees;

import com.aimprosoft.task1.dao.DepartmentDao;
import com.aimprosoft.task1.dao.EmployeeDao;
import com.aimprosoft.task1.handler.AbstractHandler;
import com.aimprosoft.task1.model.Department;
import com.aimprosoft.task1.model.Employee;
import com.aimprosoft.task1.servlet.ViewNames;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class GetEditHandler extends AbstractHandler {

    private final static String DATE_FORMAT = "yyy-MM-dd";

    private static EmployeeDao dao = getEmployeeDao();

    @Override
    public void parseParams(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        request.setAttribute("departmentName", request.getParameter("departmentName"));
        String email = request.getParameter("email");
        Employee employee = dao.getByEmail(email);
        request.setAttribute("email", email);
        request.setAttribute("newEmail", employee.getEmail());
        request.setAttribute("newName", employee.getName());
        request.setAttribute("newRoom", employee.getRoom());
        DateFormat format = new SimpleDateFormat(DATE_FORMAT);
        request.setAttribute("newBirthday", format.format(employee.getBirthday()));
        request.setAttribute("newDepartmentName", employee.getDepartmentName());
        setViewName(ViewNames.EMPLOYEE_FORM);
    }
}
