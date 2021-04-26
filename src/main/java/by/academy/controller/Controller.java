package by.academy.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.academy.controller.command.Command;
import by.academy.controller.command.CommandProvider;

public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final CommandProvider commandProvider = new CommandProvider();

    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            process(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name;
        Command command;

        name = request.getParameter("command");
        command = commandProvider.takeCommand(name);
        command.execute(request, response);
    }

}
