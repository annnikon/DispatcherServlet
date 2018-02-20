package com.aimprosoft.task1.handler.employees;

import com.aimprosoft.task1.dao.DepartmentDao;
import com.aimprosoft.task1.dao.EmployeeDao;
import com.aimprosoft.task1.handler.AbstractHandler;
import com.aimprosoft.task1.model.Department;
import com.aimprosoft.task1.model.Employee;
import com.aimprosoft.task1.servlet.ServletContext;
import com.aimprosoft.task1.servlet.ViewNames;
import com.aimprosoft.task1.utils.ValidationUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public abstract class AbstractEmployeesHandler extends AbstractHandler{

    private static EmployeeDao employeeDao = getEmployeeDao();

    private final static String EMAIL_REGEXP = "[\\w\\d_.]+[@]([\\w]+[\\.][\\w]+)+";
    private final static String NAME_REGEXP = "[\\p{L}\\s]+";
    private final static String DATE_FORMAT = "yyy-MM-dd";

    protected Employee readEmployee(HttpServletRequest request) throws ParseException {
        Employee employee = new Employee();
        employee.setName(request.getParameter("newName"));
        employee.setEmail(request.getParameter("newEmail"));
        employee.setRoom(Integer.parseInt(request.getParameter("newRoom")));
        DateFormat format = new SimpleDateFormat(DATE_FORMAT);
        employee.setBirthday(format.parse(request.getParameter("newBirthday")));
        employee.setDepartmentName(request.getParameter("newDepartmentName"));
        return employee;
    }

    protected boolean canAddEmployee(String newEmail) throws Exception {
        return (employeeDao.getByEmail(newEmail) == null);
    }

    protected boolean canEditEmployee(String email, String newEmail) throws Exception {
        return (email.equals(newEmail)) || canAddEmployee(newEmail);

    }


    protected void showAgainEmployee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("email", request.getParameter("email"));
        request.setAttribute("departmentName", request.getParameter("departmentName"));
        request.setAttribute("newEmail", request.getParameter("newEmail"));
        request.setAttribute("newName", request.getParameter("newName"));
        request.setAttribute("newRoom", request.getParameter("newRoom"));
        request.setAttribute("newBirthday", request.getParameter("newBirthday"));
        request.setAttribute("newDepartmentName", request.getParameter("newDepartmentName"));
        request.getRequestDispatcher(ViewNames.EMPLOYEE_FORM).forward(request, response);
    }

    protected boolean validateEmployee(HttpServletRequest request) {
        ValidationUtils validator = new ValidationUtils();
        validator.validateText(request.getParameter("newName"), "Name", NAME_REGEXP);
        validator.validateText(request.getParameter("newEmail"), "Email", EMAIL_REGEXP);
        validator.validateInteger(request.getParameter("newRoom"), "Room");
        validator.validateDate(request.getParameter("newBirthday"), "Birthday", DATE_FORMAT);
        validator.validateNotNull(request.getParameter("newDepartmentName"), "Department Name");
        request.setAttribute("errors", validator.getErrors());
        return validator.isValid();

    }





}
