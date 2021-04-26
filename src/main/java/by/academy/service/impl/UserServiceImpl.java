package by.academy.service.impl;

import by.academy.bean.RegistrationInfo;
import by.academy.bean.User;
import by.academy.dao.DAOException;
import by.academy.dao.DAOProvider;
import by.academy.dao.EmptyResultSetException;
import by.academy.dao.UserDAO;
import by.academy.service.input_validator.ValidatorProvider;
import by.academy.service.input_validator.impl.CheckLoginInput;
import by.academy.service.input_validator.impl.CheckRegistrationInput;
import by.academy.service.ServiceException;
import by.academy.service.UserException;
import by.academy.service.UserService;


public class UserServiceImpl implements UserService {

    @Override
    public User authorization(String login, String password) throws ServiceException, UserException {
        DAOProvider provider = DAOProvider.getInstance();
        UserDAO userDAO = provider.getUserdao();
        ValidatorProvider validatorProvider = ValidatorProvider.getInstance();
        CheckLoginInput checkLoginInput = validatorProvider.getCheckLoginInput();

        User user = null;

        if (!checkLoginInput.isInputCorrect(login, password)) {
            throw new UserException("Беды с инпутом!");
        }
        try {

            user = userDAO.authorization(login, password);
            return user;

        } catch (DAOException e) {
            throw new ServiceException(e);
        } catch (EmptyResultSetException e) {
            throw new UserException("Такого юзера не существует!");
        }
    }

    @Override
    public void registration(String login, String password, String firstName, String lastName, String email) throws UserException, ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        UserDAO userDAO = provider.getUserdao();
        ValidatorProvider validatorProvider = ValidatorProvider.getInstance();
        CheckRegistrationInput checkRegistrationInput = validatorProvider.getCheckRegistrationInput();
        User user = null;

        if (!checkRegistrationInput.isInputCorrect(login, password, firstName, lastName, email)) {
            throw new UserException("Беды с инпутом!");
        }
        RegistrationInfo registrationInfo = new RegistrationInfo(firstName, lastName, email, login, password);

        try {
            user = userDAO.authorization(login, password);


            if (!(user.getPassword() == null && user.getLogin() == null)) {
                throw new UserException("Такой юзер уже существует!");
            }
            try {
                userDAO.registration(registrationInfo);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }

        } catch (DAOException e) {
            throw new ServiceException(e);
        } catch (EmptyResultSetException e) {

        }
    }

}
