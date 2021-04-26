package by.academy.controller.properties_provider;

import by.academy.controller.properties_provider.loader.LocaleEnLoader;
import by.academy.controller.properties_provider.loader.LocaleRuLoader;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesProvider {
    private Map<PropertiesName, Properties> propertiesMap = new HashMap<>();

    public PropertiesProvider() {
        LocaleEnLoader localeEnLoader = new LocaleEnLoader();
        LocaleRuLoader localeRuLoader = new LocaleRuLoader();

        propertiesMap.put(PropertiesName.EN, localeEnLoader.get());
        propertiesMap.put(PropertiesName.RU, localeRuLoader.get());
    }

    public Properties getProperties(String name) {
        PropertiesName propertiesName;
        propertiesName = PropertiesName.valueOf(name.toUpperCase());

        return propertiesMap.get(propertiesName);
    }
}
