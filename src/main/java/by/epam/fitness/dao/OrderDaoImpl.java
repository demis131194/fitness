package by.epam.fitness.dao;

import by.epam.fitness.model.Order;
import by.epam.fitness.pool.ConnectionPool;
import by.epam.fitness.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private static Logger logger = LogManager.getLogger(OrderDaoImpl.class);
    private static final String INSERT_QUERY = "INSERT INTO orders (userId, startDate, endDate, price, description) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE orders SET userId = ?, startDate = ?, endDate = ?, price = ?, description = ?, pay = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM orders WHERE id = ? AND userId = ?";
    private static final String GET_QUERY = "SELECT id, userId, startDate, endDate, price, pay, description FROM orders WHERE id = ? AND userId = ?";
    private static final String GET_ALL_QUERY = "SELECT id, userId, startDate, endDate, price, pay, description FROM orders WHERE userId = ?";

    private static OrderDao orderDao;

    public static OrderDao getInstance() {
        if (orderDao == null) {
            orderDao = new OrderDaoImpl();
            logger.debug("OrderDao created");
        }
        return orderDao;
    }

    @Override
    public Order create(Order order) {
        Order createdOrder = null;
        ProxyConnection connection = ConnectionPool.getInstance().takeConnection();
        try {
            try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
                connection.setAutoCommit(false);
                statement.setInt(1, order.getUserId());
                statement.setDate(2, Date.valueOf(order.getStartDate()));
                statement.setDate(3, Date.valueOf(order.getEndDate()));
                statement.setInt(4, order.getPrice());
                statement.setString(5, order.getDescription());

                statement.execute();

                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    int orderId = resultSet.getInt(1);
                    order.setId(orderId);
                }

                connection.commit();
                connection.setAutoCommit(true);
                createdOrder = order;
                logger.debug("Создан order = {}", order);

            } catch (SQLException e) {
                logger.warn(e);
            }
        } finally {
            connection.release();
        }
        return createdOrder;
    }

    @Override
    public boolean update(Order order) {
        boolean isUpdated = false;
        ProxyConnection connection = ConnectionPool.getInstance().takeConnection();
        try {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
                statement.setInt(1, order.getUserId());
                statement.setDate(2, Date.valueOf(order.getStartDate()));
                statement.setDate(3, Date.valueOf(order.getEndDate()));
                statement.setInt(4, order.getPrice());
                statement.setString(5, order.getDescription());
                statement.setBoolean(6, order.isPayed());
                statement.setInt(7, order.getId());

                isUpdated = statement.execute();
                logger.debug("Обнавлен order, новый - {}", order);

            } catch (SQLException e) {
                logger.warn(e);
            }
        } finally {
            connection.release();
        }
        return isUpdated;
    }

    @Override
    public boolean delete(int orderId, int userId) {
        boolean isDeleted = false;
        ProxyConnection connection = ConnectionPool.getInstance().takeConnection();
        try {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
                statement.setInt(1, orderId);
                statement.setInt(2, userId);
                isDeleted = statement.execute();

                if (isDeleted) {
                    logger.debug("Удален order, id - {}", orderId);
                } else {
                    logger.debug(" НЕ удален order, id - {}", orderId);
                }

            } catch (SQLException e) {
                logger.warn(e);
            }
        } finally {
            connection.release();
        }
        return isDeleted;
    }

    @Override
    public Order find(int orderId, int userId) {
        Order order = null;
        ProxyConnection connection = ConnectionPool.getInstance().takeConnection();
        try {
            try (PreparedStatement statement = connection.prepareStatement(GET_QUERY)) {
                statement.setInt(1, orderId);
                statement.setInt(2, userId);

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.first()) {
                    LocalDate startDate = resultSet.getDate("startDate").toLocalDate();
                    LocalDate endDate = resultSet.getDate("endDate").toLocalDate();
                    int price = resultSet.getInt("price");
                    boolean isPayed = resultSet.getBoolean("pay");
                    String description = resultSet.getString("description");
                    order = new Order(orderId, userId, startDate, endDate, price, isPayed, description);
                    logger.debug("Get order - {}", order);
                }

            } catch (SQLException e) {
                logger.warn(e);
            }
        } finally {
            connection.release();
        }
        return order;
    }

    @Override
    public List<Order> findAll(int userId) {
        List<Order> orders = new ArrayList<>();
        ProxyConnection connection = ConnectionPool.getInstance().takeConnection();
        try {
            try (PreparedStatement statement = connection.prepareStatement(GET_ALL_QUERY)) {
                statement.setInt(1, userId);

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    LocalDate startDate = resultSet.getDate("startDate").toLocalDate();
                    LocalDate endDate = resultSet.getDate("endDate").toLocalDate();
                    int price = resultSet.getInt("price");
                    boolean isPayed = resultSet.getBoolean("pay");
                    String description = resultSet.getString("description");
                    Order order = new Order(id, userId, startDate, endDate, price, isPayed, description);
                    orders.add(order);
                }

                logger.debug("GetAll orders, userId = {} - {}", userId, orders);
            } catch (SQLException e) {
                logger.warn(e);
            }
        } finally {
            connection.release();
        }
        return orders;
    }
}
