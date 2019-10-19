package by.epam.fitness;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Main {
    private static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException, SQLException {

        logger.info("INFOOOOOOOOOOOOOOO");
        Properties properties = new Properties();
        properties.load(Main.class.getClassLoader().getResourceAsStream("config/db.properties"));
        Connection connection = DriverManager.getConnection(properties.get("url").toString(), properties);
        connection.close();
    }
}
