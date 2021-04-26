package by.academy.dao.impl;

import by.academy.bean.RegistrationInfo;
import by.academy.bean.User;
import by.academy.dao.DAOException;
import by.academy.dao.EmptyResultSetException;
import by.academy.dao.UserDAO;
import by.academy.dao.connection_pool.ConnectionPool;
import by.academy.dao.connection_pool.ConnectionPoolProvider;

import java.sql.*;

public class SQLUserDAO implements UserDAO {
    ConnectionPoolProvider provider = ConnectionPoolProvider.getInstance();
    ConnectionPool connectionPool = provider.getPool();

    @Override
    public User authorization(String login, String password) throws DAOException, EmptyResultSetException {
        String GET_USER_QUERY = "SELECT * FROM users WHERE login = ? AND password =?";
        User user = null;
        Connection connection = null;
        PreparedStatement statement;

        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(GET_USER_QUERY);

            statement.setString(1, login);
            statement.setString(2, password);
            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            resultSet.next();

            String log = resultSet.getString("login");
            String pass = resultSet.getString("password");
            String firstName = resultSet.getString("firstname");
            String lastName = resultSet.getString("lastname");
            String email = resultSet.getString("email");
            String role = resultSet.getString("role");

            return new User(firstName, lastName, email, log, pass, role);
        } catch (SQLException e) {

            if (e.getErrorCode() == 0) {
                throw new EmptyResultSetException(e.getErrorCode());
            } else {
                throw new DAOException(e.getErrorCode());
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }

    @Override
    public void registration(RegistrationInfo regInfo) throws DAOException {
        String NEW_USER_CREATE_QUERY = "INSERT INTO users( login, password, firstname, lastname, email, role) " +
                "VALUES(?,?,?,?,?,?)";

        Connection connection = null;
        PreparedStatement statement;

        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(NEW_USER_CREATE_QUERY);

            statement.setString(1, regInfo.getLogin());
            statement.setString(2, regInfo.getPassword());
            statement.setString(3, regInfo.getFirstName());
            statement.setString(4, regInfo.getLastName());
            statement.setString(5, regInfo.getEmail());
            statement.setString(6, regInfo.getRole());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }

}
