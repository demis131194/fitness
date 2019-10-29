package by.epam.fitness.pool;

import by.epam.fitness.util.PropertyLoader;
import com.mysql.cj.jdbc.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static final int DEFAULT_NUMBER_OF_CONNECTION = 5;
    private static final String PROPERTY_PATH = "db/mysql.properties";
//    private static Lock lock = new ReentrantLock();
    private static AtomicBoolean isCreated = new AtomicBoolean(false);
    private static ConnectionPool INSTANCE;

    private int numberOfConnections;

    private BlockingQueue<ProxyConnection> awaitingConnections;
    private List<ProxyConnection>  occupiedConnections = new ArrayList<>();
    private Driver driver;


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

            this.awaitingConnections = new ArrayBlockingQueue<>(numberOfConnections);

            driver = new Driver();
            DriverManager.registerDriver(driver);
            for (int i = 0; i < numberOfConnections; i++) {
                ProxyConnection connection = new ProxyConnection(DriverManager.getConnection(property.getProperty("url"), property));
                awaitingConnections.offer(connection);
            }

        } catch (SQLException e ) {
            logger.error("Missing or incorrect db configuration file.", e);
            throw new RuntimeException(e);
        }

    }

//    public static ConnectionPool getInstance() {                    // FIXME: 29.10.2019 need synchronization???
//        if (INSTANCE == null) {
//            if (lock.tryLock()) {
//                if (INSTANCE == null) {
//                    INSTANCE = new ConnectionPool();
//                    logger.debug("Connection pool created, number of connections - {}", INSTANCE.numberOfConnections);
//                }
//                lock.unlock();
//            }
//        }
//        return INSTANCE;
//    }

    public static ConnectionPool getInstance() {                    // FIXME: 29.10.2019 need synchronization???
        if (!isCreated.get()) {
            initPool();
            logger.debug("Connection pool created, number of connections - {}", INSTANCE.numberOfConnections);
        }
        return INSTANCE;
    }

    public static void initPool() {                 // FIXME: 29.10.2019 how is it?
        if (!isCreated.getAndSet(true)) {
            INSTANCE = new ConnectionPool();
        }
    }

    public Connection takeConnection() {
        ProxyConnection connection = null;
        try {
            connection = awaitingConnections.take();
            occupiedConnections.add(connection);
        } catch (InterruptedException e) {
            logger.warn("In ConnPoll takeConnection interrupted.");
        }
        return connection;
    }

    void releaseConnection(ProxyConnection connection) {
        awaitingConnections.offer(connection);
    }

    public void closeAllConnections() {
        for (int i = 0; i < numberOfConnections; i++) {
            try {
                ProxyConnection connection = awaitingConnections.take();
                connection.reallyClose();
            } catch (InterruptedException | SQLException e) {
                logger.warn(e);
            }
        }

        try {
            DriverManager.deregisterDriver(driver);
        } catch (SQLException e) {
            logger.warn(e);
        }
    }

}
