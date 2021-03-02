package by.academy.controller.propprovider.loaders;

import java.io.IOException;
import java.util.Properties;

public class LocaleRuLoader {
    private static final Properties PROPERTIES = new Properties();
    private static final String FILE_NAME = "local_ru.properties";

    static {
        loadProperties();
    }

    private static void loadProperties() {
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
