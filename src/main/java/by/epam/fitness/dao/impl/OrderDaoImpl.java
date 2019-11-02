package by.epam.fitness.dao.impl;

import by.epam.fitness.dao.OrderDao;
import by.epam.fitness.dao.TableColumn;
import by.epam.fitness.exception.DaoException;
import by.epam.fitness.model.Order;
import by.epam.fitness.model.OrderStatus;
import by.epam.fitness.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderDaoImpl implements OrderDao {
    private static Logger logger = LogManager.getLogger(OrderDaoImpl.class);
    private static final String INSERT_QUERY = "INSERT INTO orders (clientId, trainerId, clientComment) VALUES (?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE orders SET clientId = ?, trainerId = ?, exercises = ?, nutrition = ?, startDate = ?, endDate = ?, price = ?, clientComment = ?, accept = ? WHERE id = ?";
    private static final String DELETE_QUERY = "UPDATE orders SET active = false WHERE id = ?";
    private static final String FIND_QUERY = "SELECT id, clientId, trainerId, registerDate, exercises, nutrition, startDate, endDate, price, clientComment, status, accept, active FROM orders WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT id, clientId, trainerId, registerDate, exercises, nutrition, startDate, endDate, price, clientComment, status, accept, active FROM orders";
    private static final String FIND_ALL_WITH_FILTER_QUERY = "SELECT id, clientId, trainerId, registerDate, exercises, nutrition, startDate, endDate, price, clientComment, status, accept, active FROM orders WHERE id=? AND clientId=? AND trainerId=? AND startDate>=? AND endDate<=? AND price=? AND status=? AND accept=? AND active=?";
    private static final String FIND_ALL_ACTIVE_QUERY = "SELECT id, clientId, trainerId, registerDate, exercises, nutrition, startDate, endDate, price, clientComment, status, accept, active FROM orders WHERE active = 1";
    private static final String FIND_ALL_ACTIVE_BY_TRAINER_QUERY = "SELECT id, clientId, trainerId, registerDate, exercises, nutrition, startDate, endDate, price, clientComment, status, accept, active FROM orders WHERE trainerId = ? AND active = true";
    private static final String FIND_ALL_ACTIVE_BY_CLIENT_QUERY = "SELECT id, clientId, trainerId, registerDate, exercises, nutrition, startDate, endDate, price, clientComment, status, accept, active FROM orders WHERE clientId = ? AND active = true";


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

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, order.getClientId());
            statement.setInt(2, order.getTrainerId());
            statement.setString(3, order.getClientComment());
            statement.execute();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int orderId = resultSet.getInt(1);
                order.setId(orderId);
            }

            createdOrder = order;
            logger.debug("Order created = {}", order);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return createdOrder;
    }

    @Override
    public boolean update(Order order) throws DaoException {
        boolean isUpdated;

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            if (order.getClientId() != null) {
                statement.setInt(1, order.getClientId());
            } else {
                statement.setString(1, TableColumn.ORDERS_CLIENT_ID);
            }
            if (order.getTrainerId() != null) {
                statement.setInt(2, order.getTrainerId());
            } else {
                statement.setString(2, TableColumn.ORDERS_TRAINER_ID);
            }
            if (order.getExercises() != null) {
                statement.setString(3, order.getExercises());
            } else {
                statement.setString(3, TableColumn.ORDERS_EXERCISES);
            }
            if (order.getNutrition() != null) {
                statement.setString(4, order.getNutrition());
            } else {
                statement.setString(4, TableColumn.ORDERS_NUTRITION);
            }
            if (order.getStartDate() != null) {
                statement.setDate(5, Date.valueOf(order.getStartDate()));
            } else {
                statement.setString(5, TableColumn.ORDERS_START_DATE);
            }
            if (order.getEndDate() != null) {
                statement.setDate(6, Date.valueOf(order.getEndDate()));
            } else {
                statement.setString(6, TableColumn.ORDERS_END_DATE);
            }
            if (order.getPrice() != null) {
                statement.setBigDecimal(7, order.getPrice());
            } else {
                statement.setString(7, TableColumn.ORDERS_PRICE);
            }
            if (order.getClientComment() != null) {
                statement.setString(8, order.getClientComment());
            } else {
                statement.setString(8, TableColumn.ORDERS_CLIENT_COMMENT);
            }
            if (order.isAccept() != null) {
                statement.setBoolean(9, order.isAccept());
            } else {
                statement.setString(9, TableColumn.ORDERS_ACCEPT);
            }
            statement.setInt(10, Objects.requireNonNull(order.getId()));
            isUpdated = statement.execute();
            logger.debug("Order updated, new order - {}", order);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean delete(int orderId) throws DaoException {
        boolean isDeleted;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, orderId);
            isDeleted = statement.execute();

            if (isDeleted) {
                logger.debug("Order deleted, id - {}", orderId);
            } else {
                logger.debug("Unsuccessful order delete, id - {}", orderId);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isDeleted;
    }


    @Override
    public Order find(int orderId) throws DaoException {
        Order order = null;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_QUERY)) {
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.first()) {
                order = getOrderFromResultSet(resultSet);
                logger.debug("FindActive order - {}", order);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return order;
    }

    @Override
    public List<Order> findAll() throws DaoException {
        List<Order> orders = new ArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Order order = getOrderFromResultSet(resultSet);
                orders.add(order);
            }
            logger.debug("FindAll orders - {}", orders);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return orders;
    }

    @Override
    public List<Order> findAllWithFilter(Order filter) throws DaoException {
        List<Order> result = new ArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_WITH_FILTER_QUERY)) {
            if (filter.getId() != null) {
                statement.setInt(1, filter.getId());
            } else {
                statement.setString(1, TableColumn.ORDERS_ID);
            }
            if (filter.getClientId() != null) {
                statement.setInt(2, filter.getClientId());
            } else {
                statement.setString(2, TableColumn.ORDERS_CLIENT_ID);
            }
            if (filter.getTrainerId() != null) {
                statement.setInt(3, filter.getTrainerId());
            } else {
                statement.setString(3, TableColumn.ORDERS_TRAINER_ID);
            }
            if (filter.getStartDate() != null) {
                statement.setDate(4, Date.valueOf(filter.getStartDate()));
            } else {
                statement.setString(4, TableColumn.ORDERS_START_DATE);
            }
            if (filter.getEndDate() != null) {
                statement.setDate(5, Date.valueOf(filter.getEndDate()));
            } else {
                statement.setString(5, TableColumn.ORDERS_END_DATE);
            }
            if (filter.getPrice() != null) {
                statement.setBigDecimal(6, filter.getPrice());
            } else {
                statement.setString(6, TableColumn.ORDERS_PRICE);
            }
            if (filter.getOrderStatus() != null) {
                statement.setInt(7, filter.getOrderStatus().ordinal());
            } else {
                statement.setString(7, TableColumn.ORDERS_STATUS);
            }
            if (filter.isAccept() != null) {
                statement.setBoolean(8, filter.isAccept());
            } else {
                statement.setString(8, TableColumn.ORDERS_ACCEPT);
            }
            if (filter.isActive() != null) {
                statement.setBoolean(9, filter.isActive());
            } else {
                statement.setString(9, TableColumn.ORDERS_ACTIVE);
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Order order = getOrderFromResultSet(resultSet);
                result.add(order);
            }

            logger.debug("FindAllWithFilter - {}", result);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public List<Order> findAllActive() throws DaoException {
        List<Order> orders = new ArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ACTIVE_QUERY)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Order order = getOrderFromResultSet(resultSet);
                orders.add(order);
            }
            logger.debug("FindAll orders - {}", orders);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return orders;
    }

    @Override
    public List<Order> findAllActiveByTrainer(int trainerId) throws DaoException {
        List<Order> orders = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_ACTIVE_BY_TRAINER_QUERY)) {
            statement.setInt(1, trainerId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Order order = getOrderFromResultSet(resultSet);
                orders.add(order);
            }
            logger.debug("FindAllActiveByTrainer orders, trainerId = {} - {}", trainerId, orders);
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
    public List<Order> findAllActiveByClient(int clientId) throws DaoException {
        List<Order> orders = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_ACTIVE_BY_CLIENT_QUERY)) {
            statement.setInt(1, clientId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Order order = getOrderFromResultSet(resultSet);
                orders.add(order);
            }
            logger.debug("FindAllActiveByClient orders, clientId = {} - {}", clientId, orders);
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
        order.setClientId(resultSet.getInt(TableColumn.ORDERS_CLIENT_ID));
        order.setTrainerId(resultSet.getInt(TableColumn.ORDERS_TRAINER_ID));
        order.setRegisterDate(resultSet.getTimestamp(TableColumn.ORDERS_REGISTER_DATE).toLocalDateTime());
        order.setExercises(resultSet.getString(TableColumn.ORDERS_EXERCISES));
        order.setNutrition(resultSet.getString(TableColumn.ORDERS_NUTRITION));
        order.setStartDate(resultSet.getDate(TableColumn.ORDERS_START_DATE).toLocalDate());
        order.setEndDate(resultSet.getDate(TableColumn.ORDERS_END_DATE).toLocalDate());
        order.setPrice(resultSet.getBigDecimal(TableColumn.ORDERS_PRICE));
        order.setClientComment(resultSet.getString(TableColumn.ORDERS_CLIENT_COMMENT));
        order.setOrderStatus(OrderStatus.values()[resultSet.getInt(TableColumn.ORDERS_STATUS)]);
        order.setAccept(resultSet.getBoolean(TableColumn.ORDERS_ACCEPT));
        order.setActive(resultSet.getBoolean(TableColumn.ORDERS_ACTIVE));
        return order;
    }
}
