package by.academy.dao.pool;

import java.io.IOException;
import java.util.Properties;

public class MysqlPropLoader {
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try {
            PROPERTIES.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db_mysql.properties"));
        } catch (IOException e) {
            System.out.println("Properties файл не найден!");
        }
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }
}
