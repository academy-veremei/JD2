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
import java.io.IOException;
import java.util.List;

public class DeleteNews implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,
            ServiceException {
        ServiceProvider service = ServiceProvider.getInstance();
        NewsService newsService = service.getNewsService();
        MessageService messageService = service.getMessageService();

        int id = Integer.parseInt(request.getParameter("newsnum"));

        News news = new News();
        news.setId(id);

        try {
            if (newsService.delete(news)) {
                messageService.sendSuccessMessage(request, "Новость успешно удалена!");
            } else {
                messageService.sendWarningMessage(request, "При удалении новости произошла ошибка!");
            }
            response.sendRedirect("Controller?command=tomainpage");
        } catch (ServiceException e) {
            throw new ServiceException(e);
        }
    }
}
