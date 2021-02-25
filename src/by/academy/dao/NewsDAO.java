package by.academy.dao;

import by.academy.bean.News;

import java.util.List;

public interface NewsDAO {

    List<News> all()  throws DAOException;

    boolean update(News news) throws DAOException;

}
