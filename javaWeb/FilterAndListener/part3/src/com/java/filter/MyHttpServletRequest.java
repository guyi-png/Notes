package com.java.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 *  HttpServletRequestWrapper implements HttpServletRequest
 *  可供修饰某些方法
 */
public class MyHttpServletRequest extends HttpServletRequestWrapper {

    public MyHttpServletRequest(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        String parameter = super.getParameter(name);
        String s = "";
        if (parameter != null){
            if (parameter.contains("fuck")){
                s = parameter.replaceAll("fuck", "****");
            }
            if (parameter.contains("shit")){
                s = parameter.replaceAll("shit", "***");
            }
        }
        return s;
    }
}
