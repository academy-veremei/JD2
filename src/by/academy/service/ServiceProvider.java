package by.academy.service;

import by.academy.service.impl.NewsServiceImpl;
import by.academy.service.impl.UserServiceImpl;

public final class ServiceProvider {

    private static final ServiceProvider instance = new ServiceProvider();

    private ServiceProvider() {
    }

    private final UserService userService = new UserServiceImpl();
    private final NewsService newsService = new NewsServiceImpl();
    private final GatekeeperService gatekeeperService = new GatekeeperService();
    private final MessageService messageService = new MessageService();

    public static ServiceProvider getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public NewsService getNewsService() {
        return newsService;
    }

    public GatekeeperService getGatekeeperService() {
        return gatekeeperService;
    }

    public MessageService getMessageService() {return messageService;}

}
