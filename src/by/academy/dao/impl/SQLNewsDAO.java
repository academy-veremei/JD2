package by.academy.dao.impl;

import by.academy.bean.News;
import by.academy.dao.DAOException;
import by.academy.dao.NewsDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLNewsDAO implements NewsDAO {

    static {
        MYSQLDriverLoader.getInstance();
    }

    @Override
    public List<News> all() throws DAOException {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        List<News> news = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/news_management?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                    "dima", "34513451");

            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM news");

            news = new ArrayList<News>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String brief = rs.getString("brief");
                String content = rs.getString("content");
                String date = rs.getString("date");
                News n = new News(id, title, brief, content, date);
                if ("active".equals(rs.getString("status"))) {
                    news.add(n);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }

        return news;
    }

    @Override
    public boolean update(News news) throws DAOException {
        Connection con = null;
        PreparedStatement statement = null;
        final String NEWS_UPDATE_QUARY = "UPDATE news SET title=?, brief=?, content=? WHERE id=?";

        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/news_management?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                    "dima", "34513451");
            statement = con.prepareStatement(NEWS_UPDATE_QUARY);
            {
                statement.setString(1, news.getTitle());
                statement.setString(2, news.getBrief());
                statement.setString(3, news.getContent());
                statement.setInt(4, news.getId());
                statement.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }

    @Override
    public boolean delete(News news) throws DAOException {
        Connection con = null;
        PreparedStatement statement = null;
        final String NEWS_DELETE_QUARY = "UPDATE news SET status=? WHERE id=?";

        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/news_management?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                    "dima", "34513451");
            statement = con.prepareStatement(NEWS_DELETE_QUARY);
            {
                statement.setString(1, "inactive");
                statement.setInt(2, news.getId());
                statement.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }

}
