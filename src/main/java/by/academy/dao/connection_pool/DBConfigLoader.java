package by.academy.dao.connection_pool;

import java.util.ResourceBundle;

public class DBConfigLoader {
    private static final DBConfigLoader instance = new DBConfigLoader();
    private ResourceBundle bundle = ResourceBundle.getBundle("db_mysql");

    public static DBConfigLoader getInstance(){
        return instance;
    }

    public String getValue(String key){
        return bundle.getString(key);
    }
}
