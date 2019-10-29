package by.epam.fitness.dao.impl;

import by.epam.fitness.dao.TableColumn;
import by.epam.fitness.dao.UserDao;
import by.epam.fitness.exception.DaoException;
import by.epam.fitness.model.user.Client;
import by.epam.fitness.model.user.UserRole;
import by.epam.fitness.pool.ConnectionPool;
;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static Logger logger = LogManager.getLogger(UserDaoImpl.class);
    private static final String UPDATE_QUERY = "UPDATE users SET password = ?, name = ?, lastName = ?, trainerId = ?, discount = ?, phone = ? WHERE id = ?";
    private static final String DELETE_QUERY = "UPDATE users SET active = false WHERE id = ?";
    private static final String INSERT_QUERY = "INSERT INTO users (login, password, name, lastName, trainerId, role, discount, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
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
    public Client create(Client client) throws DaoException {
        Client createdClient;
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(3, client.getName());
            statement.setString(4, client.getLastName());
            statement.setInt(5, client.getTrainerId());
            statement.setInt(7, client.getDiscount());
            statement.setString(7, client.getPhone());
            statement.execute();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int orderId = resultSet.getInt(1);
                client.setId(orderId);
            }

            createdClient = client;
            logger.debug("User created - {}", client);

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.warn(e);
            }

        }
        return createdClient;
    }

    @Override
    public boolean update(Client client) throws DaoException {
        boolean isUpdated;
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(2, client.getName());
            statement.setString(3, client.getLastName());
            statement.setInt(4, client.getTrainerId());
            statement.setInt(5, client.getDiscount());
            statement.setString(6, client.getPhone());
            statement.setString(7, client.getPhone());

            isUpdated = statement.executeUpdate() == 1;


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
    public List<Client> findAllActive() throws DaoException {
        List<Client> result = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_ACTIVE_QUERY)) {

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.first()) {
                Client client = getUserFromResultSet(resultSet);
                result.add(client);
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
    public List<Client> findAllActiveWithTrainer(int trainerId) throws DaoException {
        List<Client> result = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_ACTIVE_WITH_TRAINER_QUERY)) {
            statement.setInt(1, trainerId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.first()) {
                Client client = getUserFromResultSet(resultSet);
                result.add(client);
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
    public List<Client> findAll() throws DaoException {
        List<Client> result = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.first()) {
                Client client = getUserFromResultSet(resultSet);
                result.add(client);
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
    public Client findByLogin(String userLogin) throws DaoException {
        Client client = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_LOGIN_QUERY)) {
            statement.setString(1, userLogin);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.first()) {
                client = getUserFromResultSet(resultSet);
            }

            logger.debug("FindByLogin, user - {}", client);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.warn(e);
            }

        }
        return client;
    }

    @Override
    public Client findByLoginAndPassword(String userLogin, String userPassword) throws DaoException {
        Client client = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_LOGIN_AND_PASSWORD_QUERY)) {
            statement.setString(1, userLogin);
            statement.setString(2, userPassword);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.first()) {
                client = getUserFromResultSet(resultSet);
            }

            logger.debug("FindByLogin, user - {}", client);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.warn(e);
            }

        }
        return client;
    }

    private Client getUserFromResultSet(ResultSet resultSet) throws SQLException {
        Client client = new Client();
        client.setId(resultSet.getInt(TableColumn.USERS_ID));
        client.setName(resultSet.getString(TableColumn.USERS_NAME));
        client.setLastName(resultSet.getString(TableColumn.USERS_LAST_NAME));
        client.setRegisterDateTime(resultSet.getTimestamp(TableColumn.USERS_REGISTER_DATE).toLocalDateTime());
        client.setTrainerId(resultSet.getInt(TableColumn.USERS_TRAINER_ID));
        client.setActive(resultSet.getBoolean(TableColumn.USERS_ACTIVE));
        client.setDiscount(resultSet.getInt(TableColumn.USERS_DISCOUNT));
        client.setPhone(resultSet.getString(TableColumn.USERS_PHONE));
//        user = new User(id, name, lastName, login, "********", LocalDateTime.of(registerDate, registerTime), role);    // FIXME: 22.10.2019 Password ??
        return client;
    }

}
