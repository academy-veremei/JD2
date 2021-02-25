package by.academy.controller.command.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.academy.bean.LoginationInfo;
import by.academy.bean.User;
import by.academy.controller.command.Command;
import by.academy.service.*;

public class Logination implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException {
        ServiceProvider service = ServiceProvider.getInstance();
        UserService userService = service.getUserService();
        MessageService messageService = service.getMessageService();

        LoginationInfo login = new LoginationInfo(request.getParameter("login"), request.getParameter("password"));

        User user = userService.authorization(login.getLogin(), login.getPassword());

        if (user.isExist()) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("Controller?command=toauthpage");
        } else {
            messageService.sendWarningMessage(request, "Ошибка! Проверьте правильность введенных данных!");
            response.sendRedirect("Controller?command=tomainpage");
        }

    }
}
