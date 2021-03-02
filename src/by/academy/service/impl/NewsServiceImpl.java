package by.academy.service.impl;

import by.academy.bean.News;
import by.academy.exceptions.DAOException;
import by.academy.dao.DAOProvider;
import by.academy.dao.NewsDAO;
import by.academy.service.input_validator.ValidatorProvider;
import by.academy.service.input_validator.impl.CheckModifyInput;
import by.academy.service.NewsService;
import by.academy.exceptions.NewsException;
import by.academy.exceptions.ServiceException;

import java.time.LocalDateTime;
import java.util.Map;

public class NewsServiceImpl implements NewsService {

    @Override
    public Map<Integer, News> takeAll() throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        NewsDAO newsDAO = provider.getNewsDAO();

        Map<Integer, News> news = null;

        try {
            news = newsDAO.all();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return news;
    }

    @Override
    public void update(String id, String title, String brief, String content) throws ServiceException, NewsException {
        DAOProvider provider = DAOProvider.getInstance();
        NewsDAO newsDAO = provider.getNewsDAO();
        ValidatorProvider validatorProvider = ValidatorProvider.getInstance();
        CheckModifyInput checkModifyInput = validatorProvider.getCheckModifyInput();

        if (checkModifyInput.check(title, brief, content)) {
            News news = new News(Integer.parseInt(id), title, brief, content, "");

            try {
                newsDAO.update(news);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }

        } else {
            throw new NewsException("Wrong input!");
        }
    }

    @Override
    public void delete(String id) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        NewsDAO newsDAO = provider.getNewsDAO();

        News news = new News(Integer.parseInt(id), "", "", "", "");

        try {
            newsDAO.delete(news);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void add(String id, String title, String brief, String content) throws ServiceException, NewsException {
        DAOProvider provider = DAOProvider.getInstance();
        NewsDAO newsDAO = provider.getNewsDAO();
        ValidatorProvider validatorProvider = ValidatorProvider.getInstance();
        CheckModifyInput checkModifyInput = validatorProvider.getCheckModifyInput();

        if (checkModifyInput.check(title, brief, content)) {
            String date = LocalDateTime.now().toString();
            News news = new News(0, title, brief, content, date);

            try {
                newsDAO.add(news);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }

        } else {
            throw new NewsException("Wrong input");
        }
    }

}
