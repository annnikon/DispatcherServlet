package com.aimprosoft.task1.handler.employees;

import com.aimprosoft.task1.dao.DepartmentDao;
import com.aimprosoft.task1.dao.EmployeeDao;
import com.aimprosoft.task1.handler.AbstractHandler;
import com.aimprosoft.task1.servlet.ViewNames;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class GetDeleteHandler extends AbstractHandler {

    private static EmployeeDao dao = AbstractHandler.getEmployeeDao();

    @Override
    public void parseParams(HttpServletRequest request, HttpServletResponse response) throws SQLException{

        String email = request.getParameter("email");
        try {
            dao.delete(email);
            request.setAttribute("status", "Deleted employee " + email);
        } catch (SQLException e) {
            request.setAttribute("status", "Cannot delete employee " + email);
        }
        String departmentName = request.getParameter("departmentName");
        request.setAttribute("departmentName", departmentName);
        request.setAttribute("employees", dao.list(departmentName));
        setViewName(ViewNames.EMPLOYEES);
    }
}
