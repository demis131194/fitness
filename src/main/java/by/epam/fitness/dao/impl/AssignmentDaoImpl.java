package by.epam.fitness.dao.impl;

import by.epam.fitness.dao.AssignmentDao;
import by.epam.fitness.dao.TableColumn;
import by.epam.fitness.exception.DaoException;
import by.epam.fitness.model.Assignment;
import by.epam.fitness.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssignmentDaoImpl implements AssignmentDao {
    private static Logger logger = LogManager.getLogger(AssignmentDaoImpl.class);

    private static final String INSERT_QUERY = "INSERT INTO assignments (orderId, userId, trainerId, exercises, nutrition, startDate, endDate, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE assignments SET orderId = ?, userId = ?, trainerId = ?, exercises = ?, nutrition = ?, startDate = ?, endDate = ?, price = ? WHERE id = ?";
    private static final String DELETE_QUERY = "UPDATE assignments SET active = false WHERE id = ?";
    private static final String FIND_ACTIVE_QUERY = "SELECT id, orderId, userId, trainerId, registerDate, exercises, nutrition, startDate, endDate, price, accept FROM assignments WHERE id = ? AND active = true";
    private static final String FIND_ALL_ACTIVE_QUERY = "SELECT id, orderId, userId, trainerId, registerDate, exercises, nutrition, startDate, endDate, price, accept FROM assignments WHERE active = true AND (userId = ? OR trainerId = ?)";
    private static final String FIND_ALL_QUERY = "SELECT id, orderId, userId, trainerId, registerDate, exercises, nutrition, startDate, endDate, price, accept FROM assignments";

    private static AssignmentDao assignmentDao;

    private AssignmentDaoImpl() {
    }

    public static AssignmentDao getInstance() {
        if (assignmentDao == null) {
            assignmentDao = new AssignmentDaoImpl();
            logger.debug("AssignmentDao created");
        }
        return assignmentDao;
    }

    @Override
    public Assignment create(Assignment assignment) throws DaoException {
        Assignment createdAssignment;
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            statement.setInt(1, assignment.getOrderId());
            statement.setInt(2, assignment.getUserId());
            statement.setInt(3, assignment.getTrainerId());
            statement.setString(4, assignment.getExercises());
            statement.setString(5, assignment.getNutrition());
            statement.setDate(6, Date.valueOf(assignment.getStartDate()));
            statement.setDate(7, Date.valueOf(assignment.getEndDate()));
            statement.setInt(8, assignment.getPrice());
            statement.execute();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int orderId = resultSet.getInt(1);
                assignment.setId(orderId);
            }

            connection.commit();
            connection.setAutoCommit(true);
            createdAssignment = assignment;
            logger.debug("Assignment created = {}", assignment);

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.warn(e);
            }
        }
        return createdAssignment;
    }

    @Override
    public boolean update(Assignment assignment) throws DaoException {
        boolean isUpdated;
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setInt(1, assignment.getOrderId());
            statement.setInt(2, assignment.getUserId());
            statement.setInt(3, assignment.getOrderId());
            statement.setString(4, assignment.getExercises());
            statement.setString(5, assignment.getNutrition());
            statement.setDate(6, Date.valueOf(assignment.getStartDate()));
            statement.setDate(7, Date.valueOf(assignment.getEndDate()));
            statement.setInt(8, assignment.getPrice());

            isUpdated = statement.execute();
            logger.debug("Assignment updated, new assignment - {}", assignment);

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
    public boolean delete(int assignmentId) throws DaoException {
        boolean isDeleted;
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, assignmentId);
            isDeleted = statement.execute();

            if (isDeleted) {
                logger.debug("Order deleted, id - {}", assignmentId);
            } else {
                logger.debug("Unsuccessful order delete, id - {}", assignmentId);
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
    public Assignment findActive(int assignmentId) throws DaoException {
        Assignment assignment = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement statement = connection.prepareStatement(FIND_ACTIVE_QUERY)) {
            statement.setInt(1, assignmentId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.first()) {
                assignment = getOrderFromResultSet(resultSet);
                logger.debug("FindActive assignment - {}", assignment);
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
        return assignment;
    }

    @Override
    public List<Assignment> findAllActive(Integer userId, Integer trainerId) throws DaoException {
        List<Assignment> assignments = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_ACTIVE_QUERY)) {
            statement.setInt(1, userId);
            statement.setInt(2, trainerId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Assignment assignment = getOrderFromResultSet(resultSet);
                assignments.add(assignment);
            }
            logger.debug("FindAllActive assignments - {}", assignments);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.warn(e);
            }

        }
        return assignments;
    }

    @Override
    public List<Assignment> findAll() throws DaoException {
        List<Assignment> assignments = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUERY)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Assignment assignment = getOrderFromResultSet(resultSet);
                assignments.add(assignment);
            }
            logger.debug("FindAllActive assignments - {}", assignments);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.warn(e);
            }

        }
        return assignments;
    }

    private Assignment getOrderFromResultSet(ResultSet resultSet) throws SQLException {
        Assignment assignment = new Assignment();
        assignment.setId(resultSet.getInt(TableColumn.ASSIGNMENTS_ID));
        assignment.setOrderId(resultSet.getInt(TableColumn.ASSIGNMENTS_ORDER_ID));
        assignment.setOrderId(resultSet.getInt(TableColumn.ASSIGNMENTS_USER_ID));
        assignment.setOrderId(resultSet.getInt(TableColumn.ASSIGNMENTS_TRAINER_ID));
        assignment.setRegisterDate(resultSet.getTimestamp(TableColumn.ASSIGNMENTS_REGISTER_DATE).toLocalDateTime());
        assignment.setRegisterDate(resultSet.getTimestamp(TableColumn.ORDERS_REGISTER_DATE).toLocalDateTime());
        assignment.setExercises(resultSet.getString(TableColumn.ASSIGNMENTS_EXERCISES));
        assignment.setNutrition(resultSet.getString(TableColumn.ASSIGNMENTS_NUTRITION));
        assignment.setStartDate(resultSet.getDate(TableColumn.ASSIGNMENTS_START_DATE).toLocalDate());
        assignment.setEndDate(resultSet.getDate(TableColumn.ASSIGNMENTS_END_DATE).toLocalDate());
        assignment.setActive(resultSet.getBoolean(TableColumn.ASSIGNMENTS_ACTIVE));
        assignment.setPrice(resultSet.getInt(TableColumn.ASSIGNMENTS_PRICE));
        assignment.setAccept(resultSet.getBoolean(TableColumn.ASSIGNMENTS_ACCEPT));
        return assignment;
    }
}
