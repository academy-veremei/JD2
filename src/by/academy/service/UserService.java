package by.academy.service;

import by.academy.bean.User;
import by.academy.exceptions.ServiceException;
import by.academy.exceptions.UserException;

public interface UserService {
    User authorization(String login, String password) throws ServiceException, UserException;

    void registration(String login, String password, String firstName, String lastName, String email) throws ServiceException, UserException;
}
