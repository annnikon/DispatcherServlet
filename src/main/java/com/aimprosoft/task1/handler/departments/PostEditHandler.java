package com.aimprosoft.task1.handler.departments;

import com.aimprosoft.task1.dao.DepartmentDao;
import com.aimprosoft.task1.handler.AbstractHandler;
import com.aimprosoft.task1.model.Department;
import com.aimprosoft.task1.servlet.ViewNames;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostEditHandler extends AbstractDepartmentsHandler {

    private DepartmentDao dao = getDepartmentDao();

    @Override
    public void parseParams(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!validateDepartment(request)) {
            request.setAttribute("warning","Fill these fields correctly:");
            showAgainDepartment(request,response);
            return;
        }

        String name = request.getParameter("name");
        String newName = request.getParameter("newName");

        if (!canUpdateDepartment(name,newName)) {
            request.setAttribute("warning","This name already exists: "+newName);
            showAgainDepartment(request,response);
            return;
        }

        try {
            Department department = readDepartment(request);
            System.out.println("read department: "+department);
            dao.update(name,department);
            request.setAttribute("status","Updated department "+department.getName());
        }
        catch (Exception e) {
            request.setAttribute("status","Department "+newName+" cannot be updated:" + e.getMessage());
        }
        request.setAttribute("departments", dao.list());
        setViewName(ViewNames.DEPARTMENTS);
    }
}
