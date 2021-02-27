package by.academy.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.academy.controller.command.impl.*;

public class CommandProvider {
    private Map<CommandName, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(CommandName.LOGINATION, new Logination());
        commands.put(CommandName.SAVE_NEW_USER, new SaveNewUser());
        commands.put(CommandName.TOMAINPAGE, new ToMainPage());
        commands.put(CommandName.TONEWSPAGE, new ToNewsPage());
        commands.put(CommandName.LOGOUT, new Logout());
        commands.put(CommandName.DELETENEWS, new DeleteNews());
        commands.put(CommandName.EDIT, new Edit());
    }

    public Command takeCommand(String name) {
        CommandName commandName;

        commandName = CommandName.valueOf(name.toUpperCase());
        return commands.get(commandName);
    }

}