package by.epam.fitness.dao.impl;

import by.epam.fitness.dao.OrderDao;
import by.epam.fitness.dao.TableColumn;
import by.epam.fitness.exception.DaoException;
import by.epam.fitness.model.Order;
import by.epam.fitness.model.OrderStatus;
import by.epam.fitness.pool.ConnectionPool;
import by.epam.fitness.to.OrderTo;
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
    private static final String FIND_QUERY = "SELECT o.id, o.clientId, c.name AS clientName, c.lastName AS clientLastName, o.trainerId, t.name AS trainerName, t.lastName AS trainerLastName, o.registerDate, o.exercises, o.nutrition, o.startDate, o.endDate, o.price, o.clientComment, o.status, o.accept, o.active  FROM orders o\n" +
            "LEFT JOIN clients c on o.clientId = c.clientId\n" +
            "LEFT JOIN trainers t on o.trainerId = t.trainerId\n" +
            "WHERE o.id = ?";
    private static final String FIND_ALL_QUERY = "SELECT o.id, o.clientId, c.name AS clientName, c.lastName AS clientLastName, o.trainerId, t.name AS trainerName, t.lastName AS trainerLastName, o.registerDate, o.exercises, o.nutrition, o.startDate, o.endDate, o.price, o.clientComment, o.status, o.accept, o.active  FROM orders o\n" +
            "LEFT JOIN clients c on o.clientId = c.clientId\n" +
            "LEFT JOIN trainers t on o.trainerId = t.trainerId\n";
    private static final String FIND_ALL_BY_FILTER_QUERY = "SELECT o.id, o.clientId, c.name AS clientName, c.lastName AS clientLastName, o.trainerId, t.name AS trainerName, t.lastName AS trainerLastName, o.registerDate, o.exercises, o.nutrition, o.startDate, o.endDate, o.price, o.clientComment, o.status, o.accept, o.active  FROM orders o\n" +
            "LEFT JOIN clients c on o.clientId = c.clientId\n" +
            "LEFT JOIN trainers t on o.trainerId = t.trainerId\n" +
            "WHERE c.clientId= IFNULL(?, c.clientId) AND c.name= IFNULL(?, c.name) AND c.lastName = IFNULL(?, c.lastName) AND t.trainerId= IFNULL(?, t.trainerId) AND t.name= IFNULL(?, t.name) AND t.lastName = IFNULL(?, t.lastName) AND startDate >= ? AND endDate <= ? AND price = IFNULL(?, price) AND status = IFNULL(?, status) AND accept = IFNULL(?, accept) AND o.active = ?";
    private static final String FIND_ALL_ACTIVE_QUERY = "SELECT o.id, o.clientId, c.name AS clientName, c.lastName AS clientLastName, o.trainerId, t.name AS trainerName, t.lastName AS trainerLastName, o.registerDate, o.exercises, o.nutrition, o.startDate, o.endDate, o.price, o.clientComment, o.status, o.accept, o.active  FROM orders o\n" +
            "LEFT JOIN clients c on o.clientId = c.clientId\n" +
            "LEFT JOIN trainers t on o.trainerId = t.trainerId\n" +
            "WHERE o.active = true ";
    private static final String FIND_ALL_ACTIVE_BY_TRAINER_QUERY = "SELECT o.id, o.clientId, c.name AS clientName, c.lastName AS clientLastName, o.trainerId, t.name AS trainerName, t.lastName AS trainerLastName, o.registerDate, o.exercises, o.nutrition, o.startDate, o.endDate, o.price, o.clientComment, o.status, o.accept, o.active  FROM orders o\n" +
            "LEFT JOIN clients c on o.clientId = c.clientId\n" +
            "LEFT JOIN trainers t on o.trainerId = t.trainerId\n" +
            "WHERE o.active = true AND o.trainerId = ?";
    private static final String FIND_ALL_ACTIVE_BY_CLIENT_QUERY = "SELECT o.id, o.clientId, c.name AS clientName, c.lastName AS clientLastName, o.trainerId, t.name AS trainerName, t.lastName AS trainerLastName, o.registerDate, o.exercises, o.nutrition, o.startDate, o.endDate, o.price, o.clientComment, o.status, o.accept, o.active  FROM orders o\n" +
            "LEFT JOIN clients c on o.clientId = c.clientId\n" +
            "LEFT JOIN trainers t on o.trainerId = t.trainerId\n" +
            "WHERE o.active = true AND o.clientId = ?";


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
    public OrderTo find(int orderId) throws DaoException {
        OrderTo orderTo = null;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_QUERY)) {
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.first()) {
                orderTo = getOrderToFromResultSet(resultSet);
                logger.debug("FindActive orderTo - {}", orderTo);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return orderTo;
    }

    @Override
    public List<OrderTo> findAll() throws DaoException {
        List<OrderTo> result = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                OrderTo orderTo = getOrderToFromResultSet(resultSet);
                result.add(orderTo);
            }
            logger.debug("FindAll result - {}", result);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public List<OrderTo> findAllWithFilter(OrderTo filter) throws DaoException {
        List<OrderTo> result = new ArrayList<>();

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
            if (filter.getAccept() != null) {
                statement.setBoolean(11, filter.getAccept());
            } else {
                statement.setNull(11, Types.BOOLEAN);
            }
            statement.setBoolean(12, filter.getActive());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                OrderTo orderTo = getOrderToFromResultSet(resultSet);
                result.add(orderTo);
            }

            logger.debug("FindAllWithFilter - {}", result);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public List<OrderTo> findAllActive() throws DaoException {
        List<OrderTo> result = new ArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ACTIVE_QUERY)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                OrderTo orderTo = getOrderToFromResultSet(resultSet);
                result.add(orderTo);
            }
            logger.debug("FindAll orders - {}", result);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public List<OrderTo> findAllActiveByTrainer(int trainerId) throws DaoException {
        List<OrderTo> result = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ACTIVE_BY_TRAINER_QUERY)) {
            statement.setInt(1, trainerId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                OrderTo orderTo = getOrderToFromResultSet(resultSet);
                result.add(orderTo);
            }
            logger.debug("FindAllActiveByTrainer orders, trainerId = {} - {}", trainerId, result);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public List<OrderTo> findAllActiveByClient(int clientId) throws DaoException {
        List<OrderTo> result = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ACTIVE_BY_CLIENT_QUERY)) {
            statement.setInt(1, clientId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                OrderTo orderTo = getOrderToFromResultSet(resultSet);
                result.add(orderTo);
            }
            logger.debug("FindAllActiveByClient orders, clientId = {} - {}", clientId, result);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    private OrderTo getOrderToFromResultSet(ResultSet resultSet) throws SQLException {
        OrderTo orderTo = new OrderTo();
        orderTo.setId(resultSet.getInt(TableColumn.ORDERS_ID));
        orderTo.setClientId(resultSet.getInt(TableColumn.ORDERS_CLIENT_ID));
        orderTo.setClientName(resultSet.getString(TableColumn.ORDERS_CLIENT_NAME));
        orderTo.setClientLastName(resultSet.getString(TableColumn.ORDERS_CLIENT_LAST_NAME));
        orderTo.setTrainerId(resultSet.getInt(TableColumn.ORDERS_TRAINER_ID));
        orderTo.setTrainerName(resultSet.getString(TableColumn.ORDERS_TRAINER_NAME));
        orderTo.setTrainerLastName(resultSet.getString(TableColumn.ORDERS_TRAINER_LAST_NAME));
        orderTo.setRegisterDate(resultSet.getTimestamp(TableColumn.ORDERS_REGISTER_DATE).toLocalDateTime());
        orderTo.setExercises(resultSet.getString(TableColumn.ORDERS_EXERCISES));
        orderTo.setNutrition(resultSet.getString(TableColumn.ORDERS_NUTRITION));
        orderTo.setStartDate(resultSet.getDate(TableColumn.ORDERS_START_DATE).toLocalDate());
        orderTo.setEndDate(resultSet.getDate(TableColumn.ORDERS_END_DATE).toLocalDate());
        orderTo.setPrice(resultSet.getBigDecimal(TableColumn.ORDERS_PRICE));
        orderTo.setClientComment(resultSet.getString(TableColumn.ORDERS_CLIENT_COMMENT));
        orderTo.setOrderStatus(OrderStatus.values()[resultSet.getInt(TableColumn.ORDERS_STATUS)]);
        orderTo.setAccept(resultSet.getBoolean(TableColumn.ORDERS_ACCEPT));
        orderTo.setActive(resultSet.getBoolean(TableColumn.ORDERS_ACTIVE));
        return orderTo;
    }
}
