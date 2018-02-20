package com.aimprosoft.task1.servlet;


import com.aimprosoft.task1.handler.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SafeServletContext {

    private String key;
    private AbstractHandler handler;
    private static Map<String, Class> handlers = new ConcurrentHashMap<>();
    static {

        handlers.put("default",com.aimprosoft.task1.handler.DefaultHandler.class);
        handlers.put("employeesGetEdit", com.aimprosoft.task1.handler.employees.GetEditHandler.class);
        handlers.put("employeesGetDelete", com.aimprosoft.task1.handler.employees.GetDeleteHandler.class);
        handlers.put("employeesGetAdd", com.aimprosoft.task1.handler.employees.GetAddHandler.class);
        handlers.put("employeesGetList", com.aimprosoft.task1.handler.employees.GetListHandler.class);
        handlers.put("employeesPostEdit", com.aimprosoft.task1.handler.employees.PostEditHandler.class);
        handlers.put("employeesPostAdd", com.aimprosoft.task1.handler.employees.PostAddHandler.class);

        handlers.put("departmentsGetEdit", com.aimprosoft.task1.handler.departments.GetEditHandler.class);
        handlers.put("departmentsGetDelete", com.aimprosoft.task1.handler.departments.GetDeleteHandler.class);
        handlers.put("departmentsGetAdd", com.aimprosoft.task1.handler.departments.GetAddHandler.class);
        handlers.put("departmentsGetList", com.aimprosoft.task1.handler.departments.GetListHandler.class);
        handlers.put("departmentsPostEdit", com.aimprosoft.task1.handler.departments.PostEditHandler.class);
        handlers.put("departmentsPostAdd", com.aimprosoft.task1.handler.departments.PostAddHandler.class);
    }

    public AbstractHandler getHandler() {
        return handler;
    }

    public SafeServletContext(HttpServletRequest request, String method) {

        try {
            String action = capitalize(request.getParameter("action")); // List or Add or  Edit or Delete
            String servletPath = request.getServletPath().substring(1); //employees or departments
            StringBuffer sb = new StringBuffer();
                    sb.append(servletPath) // employees or departments
                            .append(capitalize(method)) // Post Get
                            .append(action); // Edit List Delete Add
            key = sb.toString();

        } catch (Exception e) {
           key = "default";
        }
         try {
             handler = (AbstractHandler) handlers.get(key).newInstance();
         }
         catch (Exception e ) {
            handler = new ErrorHandler(e);
         }
        System.out.println("Key for handler: " + key);
        System.out.println("Handler is: " + handler.getClass());
    }

    //if line is null -- > NullPointerException --> Default handler
    private String capitalize(String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }

}
