package by.epam.fitness.dao.impl;

import by.epam.fitness.dao.TableColumnName;
import by.epam.fitness.dao.UserDao;
import by.epam.fitness.exception.DaoException;
import by.epam.fitness.model.user.User;
import by.epam.fitness.model.user.UserRole;
import by.epam.fitness.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    private static Logger logger = LogManager.getLogger(TrainerDaiImpl.class);
    private static final String FIND_BY_LOGIN_AND_PASSWORD_QUERY = "SELECT id, login, password, role FROM users WHERE login = ? AND password = ? AND active = true";
    private static final String FIND_QUERY = "SELECT id, login, password, role FROM users WHERE id = ? AND active = true";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET password = IFNULL(?, password) WHERE id = ?";
    private static final String DELETE_QUERY = "UPDATE users SET active = false WHERE id = ?";
    private static final String RESTORE_QUERY = "UPDATE users SET active = true WHERE id = ?";

    private static UserDao userDao = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDao getInstance() {
        return userDao;
    }

    @Override
    public User find(int userId) throws DaoException {
        User user = null;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_QUERY)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.first()) {
                user = getUserFromResultSet(resultSet);
                logger.debug("Find user by id - {}", user);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return user;
    }

    @Override
    public User findByLoginAndPassword(String login, String password) throws DaoException {
        User user = null;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_LOGIN_AND_PASSWORD_QUERY)) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.first()) {
                user = getUserFromResultSet(resultSet);
                logger.debug("Find user by login and pass - {}", user);
            } else {
                logger.debug("Can't find user by login - {}, and pass - {}", login, password);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return user;
    }

    @Override
    public boolean updateUser(User user) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_QUERY)) {
            statement.setString(1, user.getPassword());
            statement.setInt(2, user.getId());

            isUpdated = statement.executeUpdate() == 1;

            if (isUpdated) {
                logger.debug("Password updated for user id - {}", user.getId());
            } else {
                logger.debug("Can't update password for user id - {}", user.getId());
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean delete(int userId) throws DaoException {
        boolean isDeleted;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, userId);

            isDeleted = statement.execute();

            if (isDeleted) {
                logger.debug("User deleted, user id - {}", userId);
            } else {
                logger.debug("Can't delete user, user id - {}", userId);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isDeleted;
    }

    @Override
    public boolean restoreUser(int userId) throws DaoException {
        boolean isRestored;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(RESTORE_QUERY)) {
            statement.setInt(1, userId);

            isRestored = statement.execute();

            if (isRestored) {
                logger.debug("User restored, user id - {}", userId);
            } else {
                logger.debug("Can't restore user, user id - {}", userId);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isRestored;
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(TableColumnName.USERS_ID));
        user.setLogin(resultSet.getString(TableColumnName.USERS_LOGIN));
        user.setPassword(resultSet.getString(TableColumnName.USERS_PASSWORD));
        user.setRole(UserRole.valueOf(resultSet.getString(TableColumnName.USERS_ROLE).toUpperCase()));
        return user;
    }
}
