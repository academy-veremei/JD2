package by.academy.controller;

import by.academy.bean.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Authenticator {
    public boolean authenticate(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String name = request.getParameter("command");
        if (session.getAttribute("user") == null) {
            return name.equals("save_new_user") || name.equals("logination");
        } else {
            User user = (User) session.getAttribute("user");
            return user.isExist();
        }
    }
}
