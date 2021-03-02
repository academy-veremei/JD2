package by.academy.dao;

import by.academy.bean.RegistrationInfo;
import by.academy.bean.User;
import by.academy.exceptions.DAOException;

public interface UserDAO {

    User authorization(String login, String password) throws DAOException;

    void registration(RegistrationInfo regInfo) throws DAOException;

}
