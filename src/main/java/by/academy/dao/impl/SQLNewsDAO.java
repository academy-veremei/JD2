package by.academy.dao.impl;

import by.academy.bean.News;
import by.academy.dao.connection_pool.ConnectionPool;
import by.academy.dao.DAOException;
import by.academy.dao.NewsDAO;
import by.academy.dao.connection_pool.ConnectionPoolProvider;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class SQLNewsDAO implements NewsDAO {
    ConnectionPoolProvider provider = ConnectionPoolProvider.getInstance();
    ConnectionPool connectionPool = provider.getPool();


    @Override
    public Map<Integer, News> all() throws DAOException {
        String TAKE_NEWS_QUERY = "SELECT id,title,brief,content,date FROM news WHERE status='active'";
        Map<Integer, News> news = null;
        ResultSet rs = null;
        Connection connection = null;
        PreparedStatement statement;

        try {
            connectionPool.initPoolData();
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(TAKE_NEWS_QUERY);
            rs = statement.executeQuery(TAKE_NEWS_QUERY);

            news = new HashMap<Integer, News>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String brief = rs.getString("brief");
                String content = rs.getString("content");
                String date = rs.getString("date");
                News n = new News(id, title, brief, content, date);
                news.put(n.getId(), n);
            }
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

        return news;
    }

    @Override
    public void update(News news) throws DAOException {

        String NEWS_UPDATE_QUERY = "UPDATE news SET title=?, brief=?, content=? WHERE id=?";
        Connection connection = null;
        PreparedStatement statement;

        try {
            connectionPool.initPoolData();
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(NEWS_UPDATE_QUERY);

            statement.setString(1, news.getTitle());
            statement.setString(2, news.getBrief());
            statement.setString(3, news.getContent());
            statement.setInt(4, news.getId());
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

    @Override
    public void delete(News news) throws DAOException {

        String NEWS_DELETE_QUERY = "UPDATE news SET status=? WHERE id=?";
        Connection connection = null;
        PreparedStatement statement;

        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(NEWS_DELETE_QUERY);

            statement.setString(1, "inactive");
            statement.setInt(2, news.getId());
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

    @Override
    public void add(News news) throws DAOException {

        String NEWS_ADD_QUERY = "INSERT INTO news (title, brief, content,date) VALUES (?,?,?,?)";
        Connection connection = null;
        PreparedStatement statement;

        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(NEWS_ADD_QUERY);

            statement.setString(1, news.getTitle());
            statement.setString(2, news.getBrief());
            statement.setString(3, news.getContent());
            statement.setString(4, news.getDate());
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

    @Override
    public News one(int newsID) throws DAOException {
        String TAKE_ONE_QUERY = "SELECT id,title,brief,content,date FROM news WHERE id=?";
        Connection connection = null;
        PreparedStatement statement;

        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(TAKE_ONE_QUERY);

            statement.setString(1, String.valueOf(newsID));
            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            resultSet.next();

            int id = Integer.parseInt(resultSet.getString("id"));
            String title = resultSet.getString("title");
            String brief = resultSet.getString("brief");
            String content = resultSet.getString("content");
            String date = resultSet.getString("date");

            return new News(id, title, brief, content, date);
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
