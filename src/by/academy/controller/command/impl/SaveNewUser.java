package by.academy.controller.command.impl;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.academy.bean.RegistrationInfo;
import by.academy.controller.command.Command;
import by.academy.dao.DAOException;
import by.academy.service.*;

public class SaveNewUser implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException,
            SQLException, ServiceException, DAOException {
        ServiceProvider service = ServiceProvider.getInstance();
        UserService userService = service.getUserService();
        MessageService messageService = service.getMessageService();

        RegistrationInfo registrationInfo = new RegistrationInfo(request.getParameter("firstName"),
                request.getParameter("lastName"), request.getParameter("email"),
                request.getParameter("login"), request.getParameter("password"));

        boolean isAllFieldAreFilled = registrationInfo.getLogin() == null || registrationInfo.getPassword() == null ||
                registrationInfo.getFirstName() == null || "".equals(registrationInfo.getLogin()) ||
                "".equals(registrationInfo.getPassword()) || "".equals(registrationInfo.getFirstName());

        if (isAllFieldAreFilled) {
            messageService.sendWarningMessage(request, "Необходимо заполнить все поля регистрации!");
        } else {
            if (userService.registration(registrationInfo)) {
                messageService.sendSuccessMessage(request, "Вы успешно зарегистрированы!");
            } else {
                messageService.sendWarningMessage(request, "Ошибка регистрации!");
            }
        }
        response.sendRedirect("Controller?command=tomainpage");
    }

}
