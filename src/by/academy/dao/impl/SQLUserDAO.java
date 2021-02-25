package by.academy.dao.impl;

import by.academy.bean.RegistrationInfo;
import by.academy.bean.User;
import by.academy.dao.DAOException;
import by.academy.dao.UserDAO;

import java.sql.*;
import java.util.Queue;

public class SQLUserDAO implements UserDAO {

    static {
        MYSQLDriverLoader.getInstance();
    }

    @Override
    public User authorization(String login, String password) throws DAOException, SQLException {
        User user = null;
        Connection con = null;
        PreparedStatement statement = null;
        final String GET_USER_QUARY = "SELECT * FROM users WHERE login = ? AND password =?";

        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/news_management?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                    "dima", "34513451");
            statement = con.prepareStatement(GET_USER_QUARY);
            {
                statement.setString(1, login);
                statement.setString(2, password);
            }
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return user;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean registration(RegistrationInfo regInfo) throws SQLException {
        Connection con = null;
        PreparedStatement statement = null;
        final String NEW_USER_CREATE_QUARY = "INSERT INTO users( login, password, firstname, lastname, email, role) " +
                "VALUES(?,?,?,?,?,?)";
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/news_management?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                    "dima", "34513451");
            statement = con.prepareStatement(NEW_USER_CREATE_QUARY);
            {
                statement.setString(1, regInfo.getLogin());
                statement.setString(2, regInfo.getPassword());
                statement.setString(3, regInfo.getFirstName());
                statement.setString(4, regInfo.getLastName());
                statement.setString(5, regInfo.getEmail());
                statement.setString(6, regInfo.getRole());
                statement.executeUpdate();
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public Queue<String> check() throws DAOException {
        Connection con = null;
        Statement st = null;
        final String GET_LOGIN_QUEUE = "SELECT login FROM users";
        Queue<String> logins = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/news_management?useSSL=false&serverTimezone=UTC",
                    "root", "123456");

            st = con.createStatement();
            ResultSet rs = null;

            rs = st.executeQuery(GET_LOGIN_QUEUE);

            while (rs.next()) {
                logins.add(rs.getString("title"));
            }
            return logins;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

}
