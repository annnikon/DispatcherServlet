package com.aimprosoft.task1.handler.departments;

import com.aimprosoft.task1.handler.AbstractHandler;
import com.aimprosoft.task1.servlet.ViewNames;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAddHandler extends AbstractHandler {
    @Override
    public void parseParams(HttpServletRequest request, HttpServletResponse response) {
        setViewName(ViewNames.DEPARTMENT_FORM);
    }
}
