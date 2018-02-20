package com.aimprosoft.task1.handler.departments;

import com.aimprosoft.task1.dao.DepartmentDao;
import com.aimprosoft.task1.handler.AbstractHandler;
import com.aimprosoft.task1.servlet.ViewNames;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class GetDeleteHandler extends AbstractHandler {

    private static DepartmentDao dao = AbstractHandler.getDepartmentDao();

    @Override
    public void parseParams(HttpServletRequest request, HttpServletResponse response) throws SQLException{
        String name = request.getParameter("name");
        try {
            dao.delete(name);
            request.setAttribute("status", "Deleted department " + name);

        }
        catch (SQLException e) {
            request.setAttribute("status", name + "cannot be deleted because it has employees!");
        }
        catch (Exception e) {
            request.setAttribute("status", name + "cannot be deleted because: " + e.getMessage());
        }

        request.setAttribute("departments", dao.list());
        setViewName(ViewNames.DEPARTMENTS);
    }
}
