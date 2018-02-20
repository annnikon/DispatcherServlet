package com.aimprosoft.task1.servlet;

import com.aimprosoft.task1.handler.AbstractHandler;
import com.aimprosoft.task1.handler.DefaultHandler;


import javax.servlet.http.HttpServletRequest;


public class ServletContext {
    private static final String PACKAGE_NAME = "com.aimprosoft.task1.handler";
    private static final String SUFFIX = "Handler";


    // host:port/context/servletPath?queryString

    //          /employees?action=list [Get]
    //          /departments?action=add [Post]

    //          Result:
    //
    //          employees.GetListHandler
    //          departments.PostAddHandler etc.


    private String className;
    private AbstractHandler handler;


    public ServletContext(HttpServletRequest request, String methodName) {
        try {
            String action = capitalize(request.getParameter("action")); // List or Add or  Edit or Delete
            String servletPath = request.getServletPath().substring(1); //employees or departments
            String method = capitalize(methodName); //Get or Post

            StringBuffer sb = new StringBuffer();
                    sb.append(PACKAGE_NAME).append(".")
                            .append(servletPath).append(".")
                            .append(method)
                            .append(action).
                    append(SUFFIX);

            this.className = sb.toString(); // ==> com.aimprosoft.task1.handler.employees.GetListHandler

            handler = (AbstractHandler) Class.forName(className).newInstance();

            } catch (Exception e) {
            handler = new DefaultHandler();

        }
        System.out.println("Class name for handler: " + className);
        System.out.println("Handler is: " + handler.getClass());
    }


    //if line is null -- > NullPointerException --> Default handler
    private String capitalize(String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }

    public AbstractHandler getHandler() {
        return handler;
    }
}
