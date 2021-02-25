package by.academy.dao;

import by.academy.bean.RegistrationInfo;
import by.academy.bean.User;

import java.sql.SQLException;

public interface UserDAO {

    User authorization (String login, String password) throws DAOException, SQLException;
    boolean	registration(RegistrationInfo regInfo) throws DAOException, SQLException;

}
