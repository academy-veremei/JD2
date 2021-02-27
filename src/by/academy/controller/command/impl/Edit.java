package by.academy.controller.command.impl;

import by.academy.bean.News;
import by.academy.controller.command.Command;
import by.academy.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Edit implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException {
        ServiceProvider service = ServiceProvider.getInstance();
        NewsService newsService = service.getNewsService();
        MessageService messageService = service.getMessageService();

        String newTitle = request.getParameter("title");
        String newBrief = request.getParameter("brief");
        String newContent = request.getParameter("content");
        int id = Integer.parseInt(request.getParameter("newsnum"));
        String date = request.getParameter("newsdate");

        News news = new News(id, newTitle, newBrief, newContent, date);

        try {
            if (newsService.update(news)) {
                messageService.sendSuccessMessage(request, "Новость успешно обновлена!");
            } else {
                messageService.sendWarningMessage(request, "При обновлении новости произошла ошибка!");
            }
            response.sendRedirect("Controller?command=tomainpage");
        } catch (ServiceException e) {
            throw new ServiceException(e);
        }
    }
}
