package by.epam.fitness.util.cp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static final int NUMBER_OF_CONNECTION = 10;
    private static Lock lock = new ReentrantLock();
    private static ConnectionPool INSTANCE;

    private BlockingQueue<Connection> connections;

    private ConnectionPool() {
        this.connections = new ArrayBlockingQueue<>(NUMBER_OF_CONNECTION);
        for (int i = 0; i < NUMBER_OF_CONNECTION; i++) {
            Connection connection;
        }
    }

    public static ConnectionPool getInstance() {
        if (INSTANCE == null) {
            if (lock.tryLock()) {
                if (INSTANCE == null) {
                    INSTANCE = new ConnectionPool();
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
