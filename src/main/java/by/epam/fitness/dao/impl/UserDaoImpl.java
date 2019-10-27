package by.epam.fitness.dao.impl;

import by.epam.fitness.dao.TableColumn;
import by.epam.fitness.dao.UserDao;
import by.epam.fitness.exception.DaoException;
import by.epam.fitness.model.User;
import by.epam.fitness.model.UserRole;
import by.epam.fitness.pool.ConnectionPool;
;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static Logger logger = LogManager.getLogger(UserDaoImpl.class);
    private static final String INSERT_QUERY = "INSERT INTO users (login, password, name, lastName, trainerId, role, discount, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE users SET password = ?, name = ?, lastName = ?, trainerId = ?, discount = ?, phone = ? WHERE id = ?";
    private static final String DELETE_QUERY = "UPDATE users SET active = false WHERE id = ?";
    private static final String FIND_ALL_ACTIVE_QUERY = "SELECT id, login, name, lastName, registerDate, trainerId, role, discount, phone FROM users WHERE active = true";
    private static final String FIND_ALL_ACTIVE_WITH_TRAINER_QUERY = "SELECT id, login, name, lastName, registerDate, trainerId, role, discount, phone FROM users WHERE active = true AND trainerId = ?";
    private static final String FIND_ALL_QUERY = "SELECT id, login, name, lastName, registerDate, trainerId, role, discount, phone FROM users";
    private static final String FIND_BY_LOGIN_QUERY = "SELECT id, login, name, lastName, registerDate, trainerId, role, discount, phone FROM users WHERE login = ? AND active = true";
    private static final String FIND_BY_LOGIN_AND_PASSWORD_QUERY = "SELECT id, login, name, lastName, registerDate, trainerId, role, active, discount, phone FROM users WHERE login = ? AND password = ? AND active = true";

    private static UserDao userDao;

    private UserDaoImpl() {
    }

    public static UserDao getInstance() {
        if (userDao == null) {
            userDao = new UserDaoImpl();
            logger.debug("UserDao created");
        }
        return userDao;
    }

    @Override
    public User create(User user) throws DaoException {
        User createdUser;
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getLastName());
            statement.setInt(5, user.getTrainerId());
            statement.setString(6, user.getUserRole().name());
            statement.setInt(7, user.getDiscount());
            statement.setString(7, user.getPhone());
            statement.execute();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int orderId = resultSet.getInt(1);
                user.setId(orderId);
            }

            createdUser = user;
            logger.debug("User created - {}", user);

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.warn(e);
            }

        }
        return createdUser;
    }

    @Override
    public boolean update(User user) throws DaoException {
        boolean isUpdated;
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getName());
            statement.setString(3, user.getLastName());
            statement.setInt(4, user.getTrainerId());
            statement.setInt(5, user.getDiscount());
            statement.setString(6, user.getPhone());
            statement.setString(7, user.getPhone());

            isUpdated = statement.executeUpdate() == 1;

            logger.debug("User updated, login - {}", user.getLogin());

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
    public boolean delete(int id) throws DaoException {
        boolean isDeleted;
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, id);

            isDeleted = statement.executeUpdate() == 1;

            logger.debug("User deleted, id - {}", id);

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
    public List<User> findAllActive() throws DaoException {
        List<User> result = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_ACTIVE_QUERY)) {

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.first()) {
                User user = getUserFromResultSet(resultSet);
                result.add(user);
            }

            logger.debug("FindAllActive, user - {}", result);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.warn(e);
            }

        }
        return result;
    }

    @Override
    public List<User> findAllActiveWithTrainer(int trainerId) throws DaoException {
        List<User> result = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_ACTIVE_WITH_TRAINER_QUERY)) {
            statement.setInt(1, trainerId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.first()) {
                User user = getUserFromResultSet(resultSet);
                result.add(user);
            }

            logger.debug("FindAllActive, user - {}", result);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.warn(e);
            }

        }
        return result;
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> result = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.first()) {
                User user = getUserFromResultSet(resultSet);
                result.add(user);
            }

            logger.debug("FindAll, users - {}", result);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.warn(e);
            }

        }
        return result;
    }

    @Override
    public User findByLogin(String userLogin) throws DaoException {
        User user = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_LOGIN_QUERY)) {
            statement.setString(1, userLogin);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.first()) {
                user = getUserFromResultSet(resultSet);
            }

            logger.debug("FindByLogin, user - {}", user);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.warn(e);
            }

        }
        return user;
    }

    @Override
    public User findByLoginAndPassword(String userLogin, String userPassword) throws DaoException {
        User user = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_LOGIN_AND_PASSWORD_QUERY)) {
            statement.setString(1, userLogin);
            statement.setString(2, userPassword);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.first()) {
                user = getUserFromResultSet(resultSet);
            }

            logger.debug("FindByLogin, user - {}", user);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.warn(e);
            }

        }
        return user;
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(TableColumn.USERS_ID));
        user.setLogin(resultSet.getString(TableColumn.USERS_LOGIN));
        user.setName(resultSet.getString(TableColumn.USERS_NAME));
        user.setLastName(resultSet.getString(TableColumn.USERS_LAST_NAME));
        user.setRegisterDateTime(resultSet.getTimestamp(TableColumn.USERS_REGISTER_DATE).toLocalDateTime());
        user.setTrainerId(resultSet.getInt(TableColumn.USERS_TRAINER_ID));
        user.setUserRole(UserRole.valueOf(resultSet.getString(TableColumn.USERS_ROLE).toUpperCase()));
        user.setActive(resultSet.getBoolean(TableColumn.USERS_ACTIVE));
        user.setDiscount(resultSet.getInt(TableColumn.USERS_DISCOUNT));
        user.setPhone(resultSet.getString(TableColumn.USERS_PHONE));
//        user = new User(id, name, lastName, login, "********", LocalDateTime.of(registerDate, registerTime), role);    // FIXME: 22.10.2019 Password ??
        return user;
    }

}
