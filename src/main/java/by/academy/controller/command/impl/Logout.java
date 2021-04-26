package by.academy.controller.command.impl;

import by.academy.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Logout implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("auth");
        session.removeAttribute("role");
        session.removeAttribute("firstName");

        response.sendRedirect("Controller?command=tomainpage");
    }
}
