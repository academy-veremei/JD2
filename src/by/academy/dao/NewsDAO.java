package by.academy.dao;

import by.academy.bean.News;
import by.academy.exceptions.DAOException;

import java.util.Map;

public interface NewsDAO {

    Map<Integer, News> all() throws DAOException;

    void update(News news) throws DAOException;

    void delete(News news) throws DAOException;

    void add(News news) throws DAOException;

}
