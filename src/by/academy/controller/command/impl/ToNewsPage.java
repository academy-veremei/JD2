package by.academy.controller.command.impl;

import by.academy.bean.News;
import by.academy.controller.command.Command;
import by.academy.service.NewsService;
import by.academy.exceptions.ServiceException;
import by.academy.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class ToNewsPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceProvider service = ServiceProvider.getInstance();
        NewsService newsService = service.getNewsService();
        HttpSession session = request.getSession();

        int newsNumber = Integer.parseInt(request.getParameter("newsnum"));
        try {
            Map<Integer, News> news = newsService.takeAll();
            request.setAttribute("news", news.get(newsNumber));
        } catch (ServiceException e) {
            System.out.println("Беды с БД");
            throw new RuntimeException(" ");
        }

        StringBuilder stringBuilder = new StringBuilder(request.getServletPath());
        stringBuilder.deleteCharAt(0).append("?").append(request.getQueryString());

        session.setAttribute("url", stringBuilder.toString());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/news.jsp");
        requestDispatcher.forward(request, response);
    }
}
