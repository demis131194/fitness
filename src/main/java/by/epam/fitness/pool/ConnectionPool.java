package by.epam.fitness.pool;

import by.epam.fitness.util.PropertyLoader;
import com.mysql.cj.jdbc.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private static final String PROPERTY_PATH = "db/mysql.properties";
    private static Lock lock = new ReentrantLock();
    private static ConnectionPool INSTANCE;

    private int numberOfConnections;

    private BlockingQueue<ProxyConnection> connections;

    private ConnectionPool() {
        try {
            Properties property = PropertyLoader.loadProperty(PROPERTY_PATH);
            try {
                numberOfConnections = Integer.parseInt(property.getProperty("number.of.connections"));
                logger.debug("Number of connections in property = {}", numberOfConnections);
            } catch (NumberFormatException e) {
                numberOfConnections = DEFAULT_NUMBER_OF_CONNECTION;
                logger.debug("Incorrect number of connections, set default = {}", numberOfConnections);
            }

            this.connections = new ArrayBlockingQueue<>(numberOfConnections);

            DriverManager.registerDriver(new Driver());
//            Class.forName("com.mysql.jdbc.Driver");                                       // FIXME: 24.10.2019 Dynamic load Driver
            for (int i = 0; i < numberOfConnections; i++) {
                ProxyConnection connection = new ProxyConnection(DriverManager.getConnection(property.getProperty("url"), property));
                connections.offer(connection);
            }

        } catch (SQLException e ) {
            logger.error("Missing or incorrect db configuration file.", e);
            throw new RuntimeException(e);
        }

    }

    public static ConnectionPool getInstance() {
        if (INSTANCE == null) {
            if (lock.tryLock()) {
                if (INSTANCE == null) {
                    INSTANCE = new ConnectionPool();
                    logger.debug("Connection pool created, number of connections - {}", INSTANCE.numberOfConnections);
                }
                lock.unlock();
            }
        }
        return INSTANCE;
    }

    public ProxyConnection takeConnection() {
        ProxyConnection connection = null;                          // FIXME: 21.10.2019 Fix
        try {
            connection = connections.take();
        } catch (InterruptedException e) {
            logger.warn("In ConnPoll takeConnection interrupted.");
        }
        return connection;
    }

    public void releaseConnection(ProxyConnection connection) {
        connections.offer(connection);
    }
}
