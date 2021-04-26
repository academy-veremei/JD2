package by.academy.controller.command.impl;

import by.academy.controller.command.Command;
import by.academy.controller.properties_provider.PropertiesProvider;
import by.academy.service.*;
import by.academy.service.NewsException;
import by.academy.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;

public class Edit implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServiceProvider service = ServiceProvider.getInstance();
        NewsService newsService = service.getNewsService();
        HttpSession session = request.getSession();
        PropertiesProvider provider = new PropertiesProvider();
        Properties properties = provider.getProperties((String) session.getAttribute("locale"));

        try {
            newsService.update(request.getParameter("newsnum"), request.getParameter("title"),
                    request.getParameter("brief"), request.getParameter("content"));
            session.setAttribute("message", properties.getProperty("alert.editnews.success"));
        } catch (ServiceException e) {
            System.out.println("Беды с БД");
            session.setAttribute("message", properties.getProperty("alert.db.error"));
        } catch (NewsException n) {
            System.out.println("Беды с инпутом");
            session.setAttribute("message", properties.getProperty("alert.editnews.error"));
        }

        response.sendRedirect("Controller?command=tomainpage");
    }
}
