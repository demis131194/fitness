package by.epam.fitness.dao.impl;

import by.epam.fitness.dao.OrderDao;
import by.epam.fitness.dao.TableColumn;
import by.epam.fitness.exception.DaoException;
import by.epam.fitness.model.Order;
import by.epam.fitness.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private static Logger logger = LogManager.getLogger(OrderDaoImpl.class);
    private static final String INSERT_QUERY = "INSERT INTO orders (userId, trainerId, description) VALUES (?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE orders SET userId = ?, trainerId = ?, trainerId = ? WHERE id = ? OR trainerId = ?";
    private static final String DELETE_QUERY = "UPDATE orders SET active = false WHERE id = ? AND userId = ?";
    private static final String FIND_ACTIVE_QUERY = "SELECT id, userId, trainerId, registerDate, description, active FROM orders WHERE id = ? AND (userId = ? OR trainerId = ?) AND active = true";
    private static final String FIND_ALL_ACTIVE_QUERY = "SELECT id, userId, trainerId, registerDate, description, active FROM orders WHERE (userId = ? OR trainerId = ?) AND active = true";
    private static final String FIND_ALL_QUERY = "SELECT id, userId, trainerId, registerDate, description, active FROM orders";

    private static OrderDao orderDao;

    private OrderDaoImpl() {
    }

    public static OrderDao getInstance() {
        if (orderDao == null) {
            orderDao = new OrderDaoImpl();
            logger.debug("OrderDao created");
        }
        return orderDao;
    }

    @Override
    public Order create(Order order) throws DaoException {
        Order createdOrder;
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            statement.setInt(1, order.getUserId());
            statement.setInt(2, order.getTrainerId());
            statement.setString(3, order.getDescription());
            statement.execute();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int orderId = resultSet.getInt(1);
                order.setId(orderId);
            }

            connection.commit();
            connection.setAutoCommit(true);
            createdOrder = order;
            logger.debug("Order created = {}", order);

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.warn(e);
            }
        }
        return createdOrder;
    }

    @Override
    public boolean update(Order order) throws DaoException {
        boolean isUpdated;
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setInt(1, order.getUserId());
            statement.setInt(2, order.getTrainerId());
            statement.setString(3, order.getDescription());
            statement.setInt(4, order.getId());
            statement.setInt(5, order.getTrainerId());

            isUpdated = statement.execute();
            logger.debug("Order updated, new order - {}", order);

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.warn(e);
            }

        }
        return isUpdated;
    }

    @Override
    public boolean delete(int orderId, int userId) throws DaoException {
        boolean isDeleted;
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, orderId);
            statement.setInt(2, userId);
            isDeleted = statement.execute();

            if (isDeleted) {
                logger.debug("Order deleted, id - {}", orderId);
            } else {
                logger.debug("Unsuccessful order delete, id - {}", orderId);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.warn(e);
            }

        }
        return isDeleted;
    }

    @Override
    public Order findActive(int orderId, Integer userId, Integer trainerId) throws DaoException {
        Order order = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement statement = connection.prepareStatement(FIND_ACTIVE_QUERY)) {
            statement.setInt(1, orderId);
            statement.setInt(2, userId);
            statement.setInt(3, trainerId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.first()) {
                order = getOrderFromResultSet(resultSet);
                logger.debug("FindActive order - {}", order);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.warn(e);
            }

        }
        return order;
    }

    @Override
    public List<Order> findAllActive(Integer userId, Integer trainerId) throws DaoException {
        List<Order> orders = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_ACTIVE_QUERY)) {
            statement.setInt(1, userId);
            statement.setInt(2, trainerId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Order order = getOrderFromResultSet(resultSet);
                orders.add(order);
            }
            logger.debug("FindAllActive orders, userId = {} - {}", userId, orders);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.warn(e);
            }

        }
        return orders;
    }

    @Override
    public List<Order> findAll() throws DaoException {
        List<Order> orders = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Order order = getOrderFromResultSet(resultSet);
                orders.add(order);
            }
            logger.debug("FindAll orders - {}", orders);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.warn(e);
            }

        }
        return orders;
    }

    private Order getOrderFromResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt(TableColumn.ORDERS_ID));
        order.setUserId(resultSet.getInt(TableColumn.ORDERS_USER_ID));
        order.setTrainerId(resultSet.getInt(TableColumn.ORDERS_TRAINER_ID));
        order.setRegisterDate(resultSet.getTimestamp(TableColumn.ORDERS_REGISTER_DATE).toLocalDateTime());
        order.setDescription(resultSet.getString(TableColumn.ORDERS_DESCRIPTION));
        order.setActive(resultSet.getBoolean(TableColumn.ORDERS_ACTIVE));
        return order;
    }
}
