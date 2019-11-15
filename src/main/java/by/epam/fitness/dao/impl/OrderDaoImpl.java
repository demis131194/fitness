package by.epam.fitness.dao.impl;

import by.epam.fitness.dao.OrderDao;
import by.epam.fitness.dao.TableColumnName;
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
    private static final String INSERT_QUERY = "INSERT INTO orders (clientId, trainerId, clientComment, startDate, endDate, price) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE orders SET clientId = IFNULL(?, clientId), trainerId = IFNULL(?, trainerId), exercises = IFNULL(?, exercises), nutrition = IFNULL(?, nutrition), startDate = IFNULL(?, startDate), endDate = IFNULL(?, endDate), price = IFNULL(?, price), clientComment = IFNULL(?, clientComment), status = IFNULL(?, status) WHERE id = ?";
    private static final String DELETE_QUERY = "UPDATE orders SET active = false WHERE id = ?";
    private static final String FIND_QUERY = "SELECT o.id, o.clientId, c.name AS clientName, c.lastName AS clientLastName, o.trainerId, t.name AS trainerName, t.lastName AS trainerLastName, o.registerDate, o.exercises, o.nutrition, o.startDate, o.endDate, o.price, o.clientComment, o.status, o.active  FROM orders o\n" +
            "LEFT JOIN clients c on o.clientId = c.clientId\n" +
            "LEFT JOIN trainers t on o.trainerId = t.trainerId\n" +
            "WHERE o.id = ?";
    private static final String FIND_ALL_QUERY = "SELECT o.id, o.clientId, c.name AS clientName, c.lastName AS clientLastName, o.trainerId, t.name AS trainerName, t.lastName AS trainerLastName, o.registerDate, o.exercises, o.nutrition, o.startDate, o.endDate, o.price, o.clientComment, o.status, o.active  FROM orders o\n" +
            "LEFT JOIN clients c on o.clientId = c.clientId\n" +
            "LEFT JOIN trainers t on o.trainerId = t.trainerId\n";
    private static final String FIND_ALL_BY_FILTER_QUERY = "SELECT o.id, o.clientId, c.name AS clientName, c.lastName AS clientLastName, o.trainerId, t.name AS trainerName, t.lastName AS trainerLastName, o.registerDate, o.exercises, o.nutrition, o.startDate, o.endDate, o.price, o.clientComment, o.status, o.active  FROM orders o\n" +
            "LEFT JOIN clients c on o.clientId = c.clientId\n" +
            "LEFT JOIN trainers t on o.trainerId = t.trainerId\n" +
            "WHERE c.clientId= IFNULL(?, c.clientId) AND c.name= IFNULL(?, c.name) AND c.lastName = IFNULL(?, c.lastName) AND t.trainerId= IFNULL(?, t.trainerId) AND t.name= IFNULL(?, t.name) AND t.lastName = IFNULL(?, t.lastName) AND startDate >= ? AND endDate <= ? AND price = IFNULL(?, price) AND status = IFNULL(?, status) AND o.active = ?";
    private static final String FIND_ALL_ACTIVE_QUERY = "SELECT o.id, o.clientId, c.name AS clientName, c.lastName AS clientLastName, o.trainerId, t.name AS trainerName, t.lastName AS trainerLastName, o.registerDate, o.exercises, o.nutrition, o.startDate, o.endDate, o.price, o.clientComment, o.status, o.active  FROM orders o\n" +
            "LEFT JOIN clients c on o.clientId = c.clientId\n" +
            "LEFT JOIN trainers t on o.trainerId = t.trainerId\n" +
            "WHERE o.active = true ";
    private static final String FIND_ALL_ACTIVE_BY_TRAINER_QUERY = "SELECT o.id, o.clientId, c.name AS clientName, c.lastName AS clientLastName, o.trainerId, t.name AS trainerName, t.lastName AS trainerLastName, o.registerDate, o.exercises, o.nutrition, o.startDate, o.endDate, o.price, o.clientComment, o.status, o.active  FROM orders o\n" +
            "LEFT JOIN clients c on o.clientId = c.clientId\n" +
            "LEFT JOIN trainers t on o.trainerId = t.trainerId\n" +
            "WHERE o.active = true AND o.trainerId = ?";
    private static final String FIND_ALL_ACTIVE_BY_CLIENT_QUERY = "SELECT o.id, o.clientId, c.name AS clientName, c.lastName AS clientLastName, o.trainerId, t.name AS trainerName, t.lastName AS trainerLastName, o.registerDate, o.exercises, o.nutrition, o.startDate, o.endDate, o.price, o.clientComment, o.status, o.active  FROM orders o\n" +
            "LEFT JOIN clients c on o.clientId = c.clientId\n" +
            "LEFT JOIN trainers t on o.trainerId = t.trainerId\n" +
            "WHERE o.active = true AND o.clientId = ?";


    private static OrderDao orderDao = new OrderDaoImpl();

    private OrderDaoImpl() {
    }

    public static OrderDao getInstance() {
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
            statement.setDate(4, Date.valueOf(order.getStartDate()));
            statement.setDate(5, Date.valueOf(order.getEndDate()));
            statement.setBigDecimal(6, order.getPrice());
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
                statement.setNull(1, Types.INTEGER);
            }
            if (order.getTrainerId() != null) {
                statement.setInt(2, order.getTrainerId());
            } else {
                statement.setNull(2, Types.INTEGER);
            }
            if (order.getExercises() != null) {
                statement.setString(3, order.getExercises());
            } else {
                statement.setNull(3, Types.VARCHAR);
            }
            if (order.getNutrition() != null) {
                statement.setString(4, order.getNutrition());
            } else {
                statement.setNull(4, Types.VARCHAR);
            }
            if (order.getStartDate() != null) {
                statement.setDate(5, Date.valueOf(order.getStartDate()));
            } else {
                statement.setNull(5, Types.DATE);
            }
            if (order.getEndDate() != null) {
                statement.setDate(6, Date.valueOf(order.getEndDate()));
            } else {
                statement.setNull(6, Types.DATE);
            }
            if (order.getPrice() != null) {
                statement.setBigDecimal(7, order.getPrice());
            } else {
                statement.setNull(7, Types.DECIMAL);
            }
            if (order.getClientComment() != null) {
                statement.setString(8, order.getClientComment());
            } else {
                statement.setNull(8, Types.VARCHAR);
            }
            if (order.getOrderStatus() != null) {
                statement.setInt(9, order.getOrderStatus().ordinal());
            } else {
                statement.setNull(9, Types.INTEGER);
            }
            statement.setInt(10, Objects.requireNonNull(order.getId()));
            isUpdated = statement.executeUpdate() == 1;
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
        List<Order> result = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Order order = getOrderFromResultSet(resultSet);
                result.add(order);
            }
            logger.debug("FindAll result - {}", result);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public List<Order> findAllWithFilter(Order filter) throws DaoException {
        List<Order> result = new ArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_BY_FILTER_QUERY)) {
            if (filter.getClientId() != null) {
                statement.setInt(1, filter.getClientId());
            } else {
                statement.setNull(1, Types.INTEGER);
            }
            if (filter.getClientName() != null) {
                statement.setString(2, filter.getClientName());
            } else {
                statement.setNull(2, Types.VARCHAR);
            }
            if (filter.getClientLastName() != null) {
                statement.setString(3, filter.getClientLastName());
            } else {
                statement.setNull(3, Types.VARCHAR);
            }
            if (filter.getTrainerId() != null) {
                statement.setInt(4, filter.getTrainerId());
            } else {
                statement.setNull(4, Types.INTEGER);
            }
            if (filter.getTrainerName() != null) {
                statement.setString(5, filter.getTrainerName());
            } else {
                statement.setNull(5, Types.VARCHAR);
            }
            if (filter.getTrainerLastName() != null) {
                statement.setString(6, filter.getTrainerLastName());
            } else {
                statement.setNull(6, Types.VARCHAR);
            }
            if (filter.getStartDate() != null) {
                statement.setDate(7, Date.valueOf(filter.getStartDate()));
            } else {
                statement.setDate(7, Date.valueOf("1970-01-01"));
            }
            if (filter.getEndDate() != null) {
                statement.setDate(8, Date.valueOf(filter.getEndDate()));
            } else {
                statement.setDate(8, Date.valueOf("2100-01-01"));
            }
            if (filter.getPrice() != null) {
                statement.setBigDecimal(9, filter.getPrice());
            } else {
                statement.setNull(9, Types.DECIMAL);
            }
            if (filter.getOrderStatus() != null) {
                statement.setInt(10, filter.getOrderStatus().ordinal());
            } else {
                statement.setNull(10, Types.INTEGER);
            }
            statement.setBoolean(11, filter.getActive());
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
        List<Order> result = new ArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ACTIVE_QUERY)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Order order = getOrderFromResultSet(resultSet);
                result.add(order);
            }
            logger.debug("FindAll orders - {}", result);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public List<Order> findAllActiveByTrainer(int trainerId) throws DaoException {
        List<Order> result = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ACTIVE_BY_TRAINER_QUERY)) {
            statement.setInt(1, trainerId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Order order = getOrderFromResultSet(resultSet);
                result.add(order);
            }
            logger.debug("FindAllActiveByTrainer orders, trainerId = {} - {}", trainerId, result);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public List<Order> findAllActiveByClient(int clientId) throws DaoException {
        List<Order> result = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ACTIVE_BY_CLIENT_QUERY)) {
            statement.setInt(1, clientId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Order order = getOrderFromResultSet(resultSet);
                result.add(order);
            }
            logger.debug("FindAllActiveByClient orders, clientId = {} - {}", clientId, result);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    private Order getOrderFromResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        Date date;
        order.setId(resultSet.getInt(TableColumnName.ORDERS_ID));
        order.setClientId(resultSet.getInt(TableColumnName.ORDERS_CLIENT_ID));
        order.setClientName(resultSet.getString(TableColumnName.ORDERS_CLIENT_NAME));
        order.setClientLastName(resultSet.getString(TableColumnName.ORDERS_CLIENT_LAST_NAME));
        order.setTrainerId(resultSet.getInt(TableColumnName.ORDERS_TRAINER_ID));
        order.setTrainerName(resultSet.getString(TableColumnName.ORDERS_TRAINER_NAME));
        order.setTrainerLastName(resultSet.getString(TableColumnName.ORDERS_TRAINER_LAST_NAME));
        order.setRegisterDate(resultSet.getTimestamp(TableColumnName.ORDERS_REGISTER_DATE).toLocalDateTime());
        order.setExercises(resultSet.getString(TableColumnName.ORDERS_EXERCISES));
        order.setNutrition(resultSet.getString(TableColumnName.ORDERS_NUTRITION));
        order.setStartDate((date = resultSet.getDate(TableColumnName.ORDERS_START_DATE)) != null ? date.toLocalDate() : null );
        order.setEndDate((date = resultSet.getDate(TableColumnName.ORDERS_END_DATE)) != null ? date.toLocalDate() : null );
        order.setPrice(resultSet.getBigDecimal(TableColumnName.ORDERS_PRICE));
        order.setClientComment(resultSet.getString(TableColumnName.ORDERS_CLIENT_COMMENT));
        order.setOrderStatus(OrderStatus.values()[resultSet.getInt(TableColumnName.ORDERS_STATUS)]);
        order.setActive(resultSet.getBoolean(TableColumnName.ORDERS_ACTIVE));

        return order;
    }
}
