package by.academy.dao.pool;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static final BasicDataSource dataSource = new BasicDataSource();

    static {
        MysqlPropLoader mysqlPropLoader = new MysqlPropLoader();
        dataSource.setDriverClassName(MysqlPropLoader.get("db.driver.classname"));
        dataSource.setUrl(MysqlPropLoader.get("db.url"));
        dataSource.setUsername(MysqlPropLoader.get("db.username"));
        dataSource.setPassword(MysqlPropLoader.get("db.password"));
        dataSource.setMaxIdle(Integer.parseInt(MysqlPropLoader.get("db.idle.max")));
        dataSource.setMinIdle(Integer.parseInt(MysqlPropLoader.get("db.idle.min")));
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private ConnectionPool() {
    }
}
