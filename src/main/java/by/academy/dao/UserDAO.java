package by.academy.dao;

import by.academy.bean.RegistrationInfo;
import by.academy.bean.User;

public interface UserDAO {

    User authorization(String login, String password) throws DAOException, EmptyResultSetException;

    void registration(RegistrationInfo regInfo) throws DAOException;

}
