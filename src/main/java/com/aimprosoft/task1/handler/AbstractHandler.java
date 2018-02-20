package com.aimprosoft.task1.handler;

import com.aimprosoft.task1.dao.DepartmentDao;
import com.aimprosoft.task1.dao.EmployeeDao;
import com.aimprosoft.task1.model.Department;
import com.aimprosoft.task1.model.Employee;
import com.aimprosoft.task1.servlet.ServletContext;
import com.aimprosoft.task1.servlet.ViewNames;
import com.aimprosoft.task1.utils.ValidationUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

public abstract class AbstractHandler {

    private String viewName;

    private static DepartmentDao departmentDao;
    private static EmployeeDao employeeDao;

    static {
        try {
            Properties prop = new Properties();
            InputStream inputStream = ServletContext.class.getClassLoader().
                    getResourceAsStream("application.properties");
            prop.load(inputStream);
            departmentDao = (DepartmentDao) Class.forName(prop.getProperty("departmentDao")).newInstance();
            employeeDao = (EmployeeDao) Class.forName(prop.getProperty("employeeDao")).newInstance();

        } catch (IOException e) {
            System.out.println("no application.properties file");

        } catch (Exception e) {
            System.out.println("cannot instantiate dao");
        }

    }

    protected void setViewName(String path) {
        this.viewName=path;

    }

    public static DepartmentDao getDepartmentDao() {
        return departmentDao;
    }

    public static EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public final void handle(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            parseParams(request,response);
        }
        catch (Exception e) {
            setViewName(ViewNames.ERROR);
            request.setAttribute("error", e.getMessage() );
            request.setAttribute("stackTrace", e.getStackTrace());
        }
        request.getRequestDispatcher(viewName).forward(request, response);
    }

    abstract public void parseParams(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
