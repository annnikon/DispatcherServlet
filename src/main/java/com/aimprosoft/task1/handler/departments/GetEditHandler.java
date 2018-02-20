package com.aimprosoft.task1.handler.departments;

import com.aimprosoft.task1.dao.DepartmentDao;
import com.aimprosoft.task1.handler.AbstractHandler;
import com.aimprosoft.task1.model.Department;
import com.aimprosoft.task1.servlet.ViewNames;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class GetEditHandler extends AbstractHandler {

    private static DepartmentDao dao = getDepartmentDao();

    @Override
    public void parseParams(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        String name = request.getParameter("name");
        Department department = dao.getByName(name);
        request.setAttribute("name",name);
        request.setAttribute("newName",department.getName());
        request.setAttribute("newInfo",department.getInfo());
        setViewName(ViewNames.DEPARTMENT_FORM);
    }
}
