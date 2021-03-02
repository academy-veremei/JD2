package by.academy.dao.impl;

import by.academy.bean.News;
import by.academy.dao.pool.ConnectionPool;
import by.academy.exceptions.DAOException;
import by.academy.dao.NewsDAO;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class SQLNewsDAO implements NewsDAO {
    @Override
    public Map<Integer, News> all() throws DAOException {
        ResultSet rs = null;
        final String TAKE_NEWS_QUERY = "SELECT id,title,brief,content,date FROM news WHERE status='active'";

        Map<Integer, News> news = null;

        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
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
        }

        return news;
    }

    @Override
    public void update(News news) throws DAOException {

        final String NEWS_UPDATE_QUERY = "UPDATE news SET title=?, brief=?, content=? WHERE id=?";

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(NEWS_UPDATE_QUERY)) {

            statement.setString(1, news.getTitle());
            statement.setString(2, news.getBrief());
            statement.setString(3, news.getContent());
            statement.setInt(4, news.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(News news) throws DAOException {

        final String NEWS_DELETE_QUERY = "UPDATE news SET status=? WHERE id=?";

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(NEWS_DELETE_QUERY)) {

            statement.setString(1, "inactive");
            statement.setInt(2, news.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void add(News news) throws DAOException {

        final String NEWS_ADD_QUERY = "INSERT INTO news (title, brief, content,date) VALUES (?,?,?,?)";

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(NEWS_ADD_QUERY)) {

            statement.setString(1, news.getTitle());
            statement.setString(2, news.getBrief());
            statement.setString(3, news.getContent());
            statement.setString(4, news.getDate());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

}
