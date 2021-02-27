package by.academy.controller.command.impl;

import by.academy.bean.News;
import by.academy.controller.command.Command;
import by.academy.service.MessageService;
import by.academy.service.NewsService;
import by.academy.service.ServiceException;
import by.academy.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ToMainPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        ServiceProvider service = ServiceProvider.getInstance();
        NewsService newsService = service.getNewsService();
        MessageService messageService = service.getMessageService();

        try {
            List<News> news = newsService.takeAll();
            request.setAttribute("news", news);
            request.setAttribute("message",messageService.getMessage(request));
        } catch (ServiceException e) {
            throw new ServiceException(e);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
        requestDispatcher.forward(request, response);
    }
}
