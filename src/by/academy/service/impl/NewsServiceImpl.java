package by.academy.service.impl;

import by.academy.bean.News;
import by.academy.dao.DAOException;
import by.academy.dao.DAOProvider;
import by.academy.dao.NewsDAO;
import by.academy.service.NewsService;
import by.academy.service.ServiceException;

import java.util.List;

public class NewsServiceImpl implements NewsService {

    @Override
    public List<News> takeAll() throws ServiceException {

        DAOProvider provider = DAOProvider.getInstance();
        NewsDAO newsDAO = provider.getNewsDAO();

        List<News> news;
        try {
            news = newsDAO.all();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return news;
    }

    @Override
    public boolean update(News news) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        NewsDAO newsDAO = provider.getNewsDAO();

        try {
            return newsDAO.update(news);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

}
