package by.academy.service.impl;

import by.academy.bean.RegistrationInfo;
import by.academy.bean.User;
import by.academy.dao.DAOException;
import by.academy.dao.DAOProvider;
import by.academy.dao.UserDAO;
import by.academy.service.ServiceException;
import by.academy.service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    @Override
    public User authorization(String login, String password) throws ServiceException {
        if (login == null || "".equals(login) || password == null || "".equals(password)) {
//            throw new ServiceException("wrong login or password");
            return new User();
        } else {
            DAOProvider provider = DAOProvider.getInstance();
            UserDAO userDAO = provider.getUserdao();

            User user = null;
            try {
                user = userDAO.authorization(login, password);
                user.setExist(user.getPassword() != null && user.getLogin().equals(login) && user.getPassword()
                        .equals(password) && user.getLogin().equals(login));
            } catch (DAOException | SQLException e) {
                throw new ServiceException("error message", e);
            }
            return user;
        }
    }

    @Override
    public boolean registration(RegistrationInfo regInfo) throws SQLException, DAOException {
        DAOProvider provider = DAOProvider.getInstance();
        UserDAO userDAO = provider.getUserdao();
        boolean isLoginExists = true;
        return userDAO.registration(regInfo);
    }

}
