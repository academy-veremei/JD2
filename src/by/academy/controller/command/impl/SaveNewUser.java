package by.academy.controller.command.impl;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.academy.controller.command.Command;
import by.academy.controller.propprovider.PropertiesProvider;
import by.academy.service.*;
import by.academy.exceptions.ServiceException;
import by.academy.exceptions.UserException;

public class SaveNewUser implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServiceProvider service = ServiceProvider.getInstance();
        UserService userService = service.getUserService();
        HttpSession session = request.getSession();
        PropertiesProvider provider = new PropertiesProvider();
        Properties properties = provider.getProperties((String) session.getAttribute("locale"));

        try {
            userService.registration(request.getParameter("login"), request.getParameter("password"),
                    request.getParameter("firstName"), request.getParameter("lastName"),
                    request.getParameter("email"));
            session.setAttribute("message", properties.getProperty("alert.registration.success"));
        } catch (ServiceException e) {
            System.out.println("Беды с БД");
            session.setAttribute("message", properties.getProperty("alert.db.error"));
        } catch (UserException e) {
            System.out.println("Беды с инпутом");
            session.setAttribute("message", properties.getProperty("alert.registration.error"));
        }

        response.sendRedirect("Controller?command=tomainpage");
    }

}
