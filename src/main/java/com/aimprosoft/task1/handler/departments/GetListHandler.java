package com.aimprosoft.task1.handler.departments;

import com.aimprosoft.task1.dao.DepartmentDao;
import com.aimprosoft.task1.handler.AbstractHandler;
import com.aimprosoft.task1.servlet.ViewNames;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class GetListHandler extends AbstractHandler {

    private static DepartmentDao dao = getDepartmentDao();

    @Override
    public void parseParams(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        request.setAttribute("departments", dao.list());
        setViewName(ViewNames.DEPARTMENTS);

    }
}
