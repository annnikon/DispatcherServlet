package com.aimprosoft.task1.handler.departments;

import com.aimprosoft.task1.dao.DepartmentDao;
import com.aimprosoft.task1.handler.AbstractHandler;
import com.aimprosoft.task1.model.Department;
import com.aimprosoft.task1.servlet.ViewNames;
import com.aimprosoft.task1.utils.ValidationUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractDepartmentsHandler extends AbstractHandler{

    private DepartmentDao departmentDao = getDepartmentDao();

    protected Department readDepartment(HttpServletRequest request) {

        String newName = request.getParameter("newName");
        String newInfo = request.getParameter("newInfo");
        Department department = new Department();
        department.setName(newName);
        department.setInfo(newInfo);
        return department;
    }

    protected boolean canAddDepartment(String newName) throws Exception {
        return (departmentDao.getByName(newName) == null);
    }

    protected boolean canUpdateDepartment(String name, String newName) throws Exception {
        return name.equals(newName) || canAddDepartment(newName);
    }

    protected boolean validateDepartment(HttpServletRequest request) {
        ValidationUtils validator = new ValidationUtils();
        validator.validateNotNull(request.getParameter("newName"), "Name");
        request.setAttribute("errors",validator.getErrors());
        return validator.isValid();

    }

    protected void showAgainDepartment(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("name",request.getParameter("name"));
        request.setAttribute("newName",request.getParameter("newName"));
        request.setAttribute("newInfo",request.getParameter("newInfo"));
        request.getRequestDispatcher(ViewNames.DEPARTMENT_FORM).forward(request,response);
    }
}
