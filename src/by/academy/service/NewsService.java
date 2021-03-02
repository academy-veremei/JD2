package by.academy.service;

import by.academy.bean.News;
import by.academy.exceptions.NewsException;
import by.academy.exceptions.ServiceException;

import java.util.Map;

public interface NewsService {
    Map<Integer, News> takeAll() throws ServiceException;

    void update(String id, String title, String brief, String content) throws ServiceException, NewsException;

    void delete(String id) throws ServiceException;

    void add(String id, String title, String brief, String content) throws ServiceException, NewsException;
}

