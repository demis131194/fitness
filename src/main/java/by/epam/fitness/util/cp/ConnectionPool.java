package by.epam.fitness.util.cp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static final int DEFAULT_NUMBER_OF_CONNECTION = 5;
    private static Lock lock = new ReentrantLock();
    private static ConnectionPool INSTANCE;

    private BlockingQueue<Connection> connections;

    private ConnectionPool() {
        this.connections = new ArrayBlockingQueue<>(DEFAULT_NUMBER_OF_CONNECTION);
        Properties property = new Properties();
        try {
            property.load(ConnectionPool.class.getClassLoader().getResourceAsStream("config/db.properties"));
            Integer numberOfConnections = null;
            try {
                numberOfConnections = Integer.parseInt(property.getProperty("number.of.connections"));
                logger.debug("Number of connections in property = {}", numberOfConnections);
            } catch (NumberFormatException e) {
                numberOfConnections = DEFAULT_NUMBER_OF_CONNECTION;
                logger.debug("Number of connections set default = {}", numberOfConnections);
            }

            for (int i = 0; i < numberOfConnections; i++) {
                Connection connection = DriverManager.getConnection(property.getProperty("url"), property);
                connections.offer(connection);
            }

        } catch (IOException e) {
            logger.error("Отсутствует вайл конфигурации базы данных", e);
        } catch (SQLException e) {
            logger.error("Неверный файл конфигурации.", e);
        }

    }

    public static ConnectionPool getInstance() {
        if (INSTANCE == null) {
            if (lock.tryLock()) {
                if (INSTANCE == null) {
                    INSTANCE = new ConnectionPool();
                    logger.info("Connection pool created, number of connections - {}", DEFAULT_NUMBER_OF_CONNECTION);
                }
                lock.unlock();
            }
        }
        return INSTANCE;
    }


    public Connection getConnection() throws InterruptedException {
        return connections.take();
    }

    public void closeConnection(Connection connection) {
        connections.offer(connection);
    }
}
