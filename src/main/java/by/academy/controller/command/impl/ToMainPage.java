package by.academy.controller.command.impl;

import by.academy.bean.News;
import by.academy.controller.RequestSaver;
import by.academy.controller.command.Command;
import by.academy.service.NewsService;
import by.academy.service.ServiceException;
import by.academy.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class ToMainPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceProvider service = ServiceProvider.getInstance();
        NewsService newsService = service.getNewsService();

        try {
            Map<Integer, News> news = newsService.takeAll();
            request.setAttribute("news", news);
        } catch (ServiceException e) {
            System.out.println("Беды с БД");
            throw new ServletException(e);
        }

        RequestSaver.saveURL(request);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
        requestDispatcher.forward(request, response);
    }
}
