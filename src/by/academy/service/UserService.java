package by.academy.service;

import by.academy.bean.RegistrationInfo;
import by.academy.bean.User;
import by.academy.dao.DAOException;

import java.sql.SQLException;

public interface UserService {
    User authorization(String login, String passport) throws ServiceException;
    boolean registration(RegistrationInfo regInfo) throws ServiceException, SQLException, DAOException;
}
