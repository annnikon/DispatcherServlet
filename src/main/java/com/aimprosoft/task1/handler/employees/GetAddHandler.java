package com.aimprosoft.task1.handler.employees;

import com.aimprosoft.task1.handler.AbstractHandler;
import com.aimprosoft.task1.servlet.ViewNames;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAddHandler extends AbstractHandler {
    @Override
    public void parseParams(HttpServletRequest request, HttpServletResponse response) {
        String departmentName = request.getParameter("departmentName");
        request.setAttribute("departmentName", departmentName);
        request.setAttribute("newDepartmentName", departmentName);
        setViewName(ViewNames.EMPLOYEE_FORM);
    }
}
