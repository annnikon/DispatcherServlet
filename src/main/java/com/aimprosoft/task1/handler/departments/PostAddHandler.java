package com.aimprosoft.task1.handler.departments;

import com.aimprosoft.task1.dao.DepartmentDao;
import com.aimprosoft.task1.handler.AbstractHandler;
import com.aimprosoft.task1.handler.employees.AbstractEmployeesHandler;
import com.aimprosoft.task1.model.Department;
import com.aimprosoft.task1.servlet.ViewNames;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostAddHandler extends AbstractDepartmentsHandler {

    private static DepartmentDao dao = getDepartmentDao();

    @Override
    public void parseParams(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!validateDepartment(request)) {
            request.setAttribute("warning","Please fill the fields correctly:");
            showAgainDepartment(request,response);
            return;
        }
        String newName = request.getParameter("newName");
        if (!canAddDepartment(newName)) {
            request.setAttribute("warning","This name already exists: "+newName);
            showAgainDepartment(request,response);
            return;
        }
        try {
            Department department = readDepartment(request);
            dao.add(department);
            request.setAttribute("status","Added department "+department.getName());
        }
        catch (Exception e) {
            request.setAttribute("status","Department "+newName+" was not added:" + e.getMessage());
        }
        request.setAttribute("departments", dao.list());
        setViewName(ViewNames.DEPARTMENTS);

    }
}
