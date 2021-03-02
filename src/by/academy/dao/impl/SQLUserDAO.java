package by.academy.dao.impl;

import by.academy.bean.RegistrationInfo;
import by.academy.bean.User;
import by.academy.exceptions.DAOException;
import by.academy.dao.UserDAO;
import by.academy.dao.pool.ConnectionPool;

import java.sql.*;

public class SQLUserDAO implements UserDAO {

    @Override
    public User authorization(String login, String password) throws DAOException {
        User user = null;

        final String GET_USER_QUERY = "SELECT * FROM users WHERE login = ? AND password =?";

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_USER_QUERY);) {

            statement.setString(1, login);
            statement.setString(2, password);
            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            resultSet.next();

            user = new User();
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setFirstName(resultSet.getString("firstname"));
            user.setLastName(resultSet.getString("lastname"));
            user.setEmail(resultSet.getString("email"));
            user.setRole(resultSet.getString("role"));
            return user;
        } catch (SQLException e) {

            if (e.getErrorCode() == 0) {
                return new User();
            } else {
                throw new DAOException(e.getErrorCode());
            }

        }
    }

    @Override
    public void registration(RegistrationInfo regInfo) throws DAOException {
        final String NEW_USER_CREATE_QUERY = "INSERT INTO users( login, password, firstname, lastname, email, role) " +
                "VALUES(?,?,?,?,?,?)";

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(NEW_USER_CREATE_QUERY);) {

            statement.setString(1, regInfo.getLogin());
            statement.setString(2, regInfo.getPassword());
            statement.setString(3, regInfo.getFirstName());
            statement.setString(4, regInfo.getLastName());
            statement.setString(5, regInfo.getEmail());
            statement.setString(6, regInfo.getRole());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

}
