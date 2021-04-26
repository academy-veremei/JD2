package by.academy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RequestSaver {
    private RequestSaver(){}
    public static void saveURL(HttpServletRequest request){
        HttpSession session = request.getSession();
        StringBuilder stringBuilder = new StringBuilder(request.getServletPath());
        stringBuilder.deleteCharAt(0).append("?").append(request.getQueryString());
        session.setAttribute("url", stringBuilder.toString());
    }
}
