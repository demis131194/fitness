package by.epam.fitness.dao.impl;

import by.epam.fitness.dao.TableColumn;
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
    private static final String FIND_BY_LOGIN_AND_PASSWORD_QUERY = "SELECT id, role FROM users WHERE login = ? AND password = ? AND active = true";
    private static final String UPDATE_PASSWORD_QUERY = "UPDATE users SET password = ? WHERE id = ?";

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
    public boolean updatePassword(int userId, String password) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PASSWORD_QUERY)) {
            statement.setString(1, password);
            statement.setInt(2, userId);

            isUpdated = statement.execute();

            if (isUpdated) {
                logger.debug("Password updated for user id - {}", userId);
            } else {
                logger.debug("Can't update password for user id - {}", userId);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isUpdated;
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(TableColumn.USERS_ID));
        user.setRole(UserRole.valueOf(resultSet.getString(TableColumn.USERS_ROLE).toUpperCase()));
        return user;
    }
}
