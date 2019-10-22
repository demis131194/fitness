package by.epam.fitness.dao;

import by.epam.fitness.exception.DaoException;
import by.epam.fitness.model.User;
import by.epam.fitness.model.UserRole;
import by.epam.fitness.pool.ConnectionPool;
import by.epam.fitness.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static Logger logger = LogManager.getLogger(UserDaoImpl.class);
    private static final String INSERT_QUERY = "INSERT INTO users (login, password, name, lastName, registerDate, registerTime, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE users SET login = ?, password = ?, name = ?, lastName = ?, role = ? WHERE id = ?";
    private static final String DELETE_QUERY = "UPDATE users SET active = 0 WHERE id = ?";
    private static final String FIND_ALL_ACTIVE_QUERY = "SELECT id, login, name, lastName, registerDate, registerTime, role FROM users WHERE active = 1";
    private static final String FIND_ALL_QUERY = "SELECT id, login, name, lastName, registerDate, registerTime, role FROM users";
    private static final String FIND_BY_LOGIN_QUERY = "SELECT id, login, name, lastName, registerDate, registerTime, role FROM users WHERE login = ? AND active = 1";
    private static final String FIND_BY_LOGIN_AND_PASSWORD_QUERY = "SELECT id, login, name, lastName, registerDate, registerTime, role FROM users WHERE login = ? AND password = ? AND active = 1";

    private static UserDao userDao;

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
        ProxyConnection connection = ConnectionPool.getInstance().takeConnection();
        try {
            try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, user.getLogin());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getName());
                statement.setString(4, user.getLastName());
                statement.setDate(5, Date.valueOf(user.getRegisterDateTime().toLocalDate()));
                statement.setTime(6, Time.valueOf(user.getRegisterDateTime().toLocalTime()));
                statement.setString(7, user.getUserRole().name());

                statement.execute();

                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    int orderId = resultSet.getInt(1);
                    user.setId(orderId);
                }

                createdUser = user;
                logger.debug("User created - {}", user);

            } finally {
                connection.release();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return createdUser;
    }

    @Override
    public boolean update(User user) throws DaoException {
        boolean isUpdated;
        ProxyConnection connection = ConnectionPool.getInstance().takeConnection();
        try {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
                statement.setString(1, user.getLogin());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getName());
                statement.setString(4, user.getLastName());
                statement.setString(5, user.getUserRole().name());
                statement.setInt(6, user.getId());

                isUpdated = statement.executeUpdate() == 1;

                logger.debug("User updated, login - {}", user.getLogin());

            } finally {
                connection.release();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean delete(int id) throws DaoException {
        boolean isDeleted;
        ProxyConnection connection = ConnectionPool.getInstance().takeConnection();
        try {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
                statement.setInt(1, id);

                isDeleted = statement.executeUpdate() == 1;

                logger.debug("User deleted, id - {}", id);

            } finally {
                connection.release();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isDeleted;
    }

    @Override
    public List<User> findAllActive() throws DaoException {
        List<User> users = findAllUsers(FIND_ALL_ACTIVE_QUERY);
        return users;
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = findAllUsers(FIND_ALL_QUERY);
        return users;
    }

    @Override
    public User findByLogin(String userLogin) throws DaoException {
        User user = null;
        ProxyConnection connection = ConnectionPool.getInstance().takeConnection();
        try {
            try (PreparedStatement statement = connection.prepareStatement(FIND_BY_LOGIN_QUERY)) {
                statement.setString(1, userLogin);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.first()) {
                    user = getUserFromResultSet(resultSet);
                }

                logger.debug("FindByLogin, user - {}", user);
            } finally {
                connection.release();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return user;
    }

    @Override
    public User findByLoginAndPassword(String userLogin, String userPassword) throws DaoException {
        User user = null;
        ProxyConnection connection = ConnectionPool.getInstance().takeConnection();
        try {
            try (PreparedStatement statement = connection.prepareStatement(FIND_BY_LOGIN_AND_PASSWORD_QUERY)) {
                statement.setString(1, userLogin);
                statement.setString(2, userPassword);

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.first()) {
                    user = getUserFromResultSet(resultSet);
                }

                logger.debug("FindByLogin, user - {}", user);
            } finally {
                connection.release();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return user;
    }

    private List<User> findAllUsers(String query) throws DaoException {
        List<User> users = new ArrayList<>();
        ProxyConnection connection = ConnectionPool.getInstance().takeConnection();
        try {
            try (PreparedStatement statement = connection.prepareStatement(query)) {

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    User user = getUserFromResultSet(resultSet);
                    users.add(user);
                }
                logger.debug("FindAll users - {}", users);
            } finally {
                connection.release();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return users;
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user;
        int id = resultSet.getInt("id");
        String login = resultSet.getString("login");
        String name = resultSet.getString("name");
        String lastName = resultSet.getString("lastName");
        LocalDate registerDate = resultSet.getDate("registerDate").toLocalDate();
        LocalTime registerTime = resultSet.getTime("registerTime").toLocalTime();
        UserRole role = UserRole.valueOf(resultSet.getString("role").toUpperCase());
        user = new User(id, name, lastName, login, "********", LocalDateTime.of(registerDate, registerTime), role);    // FIXME: 22.10.2019 Password ??
        return user;
    }

}
