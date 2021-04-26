package by.academy.dao.connection_pool;

import java.sql.SQLException;

public class ConnectionPoolProvider {
    private static final ConnectionPoolProvider instance = new ConnectionPoolProvider();
    private final ConnectionPool pool = new ConnectionPool();

    private ConnectionPoolProvider() {
    }

    public static ConnectionPoolProvider getInstance() {
        return instance;
    }

    public ConnectionPool getPool() {
        return pool;
    }

    public void initPool() {
        try {
            pool.initPoolData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void disposePool(){
        try {
        pool.dispose();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
