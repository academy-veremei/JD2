package by.academy.controller.command.impl;

import by.academy.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeLocale implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String locale = request.getParameter("locale");
        session.setAttribute("locale", locale);

        String url = (String) session.getAttribute("url");
        response.sendRedirect(url);
    }
}
