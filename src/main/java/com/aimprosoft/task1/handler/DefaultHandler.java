package com.aimprosoft.task1.handler;

import com.aimprosoft.task1.servlet.ViewNames;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DefaultHandler extends AbstractHandler {

    @Override
   public void parseParams(HttpServletRequest request, HttpServletResponse response){
        setViewName(ViewNames.MAIN);
    }
}
