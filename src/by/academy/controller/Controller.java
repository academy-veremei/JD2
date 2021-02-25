package by.academy.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.academy.controller.command.Command;
import by.academy.controller.command.CommandProvider;
import by.academy.dao.DAOException;
import by.academy.service.ServiceException;

public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final CommandProvider commandProvider = new CommandProvider();
    private final Authenticator authenticator = new Authenticator();

    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            process(request, response);
        } catch (SQLException | ServiceException | DAOException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            process(request, response);
        } catch (SQLException | ServiceException | DAOException throwables) {
            throwables.printStackTrace();
        }
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ServiceException, DAOException {
        String name;
        Command command;

        if (authenticator.authenticate(request)) {
            name = request.getParameter("command");
            command = commandProvider.takeCommand(name);
        } else {
            command = commandProvider.takeCommand("tomainpage");
        }
        command.execute(request, response);
    }

}
