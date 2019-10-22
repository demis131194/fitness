package by.epam.fitness.util;

import by.epam.fitness.pool.ConnectionPool;

import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {

    private PropertyLoader() {}

    public static Properties loadProperty(String propertyPath) throws IOException {
        Properties properties = new Properties();
        properties.load(ConnectionPool.class.getClassLoader().getResourceAsStream(propertyPath));                       // FIXME: 22.10.2019 return null? NPE
        return properties;
    }
}
