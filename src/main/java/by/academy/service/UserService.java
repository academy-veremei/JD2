package by.academy.service;

import by.academy.bean.User;

public interface UserService {
    User authorization(String login, String password) throws ServiceException, UserException;

    void registration(String login, String password, String firstName, String lastName, String email) throws ServiceException, UserException;
}
