package com.aimprosoft.task1.handler;

import com.aimprosoft.task1.servlet.ViewNames;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorHandler extends AbstractHandler {

    private Throwable throwable;

    public ErrorHandler(Throwable throwable) {
        this.throwable = throwable;
    }


    public void parseParams(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("error", throwable.getMessage() );
        request.setAttribute("stackTrace", throwable.getStackTrace());
        setViewName(ViewNames.ERROR);
    }


}
