package by.academy.controller.properties_provider.loader;

import java.io.IOException;
import java.util.Properties;

public class LocaleEnLoader {
    private static final Properties PROPERTIES = new Properties();
    private static final String FILE_NAME = "local_en.properties";

    static {
        try {
            PROPERTIES.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(FILE_NAME));
        } catch (IOException e) {
            System.out.println(FILE_NAME + "не найден");
        }
    }

    public Properties get() {
        return PROPERTIES;
    }
}
