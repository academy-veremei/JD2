package by.academy.service;

import by.academy.bean.News;

import java.util.List;

public interface NewsService {
    List<News> takeAll() throws ServiceException;
    boolean update(News news) throws ServiceException;
    boolean delete(News news) throws ServiceException;
}

