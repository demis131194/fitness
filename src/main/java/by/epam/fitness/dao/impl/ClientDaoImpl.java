package by.epam.fitness.dao.impl;

import by.epam.fitness.dao.TableColumn;
import by.epam.fitness.dao.ClientDao;
import by.epam.fitness.exception.DaoException;
import by.epam.fitness.model.user.Client;
import by.epam.fitness.pool.ConnectionPool;
;
import com.mysql.cj.MysqlType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl implements ClientDao {
    private static Logger logger = LogManager.getLogger(ClientDaoImpl.class);

    private static final String INSERT_USERS_QUERY = "INSERT INTO users (login, password, role) VALUES (?, ?, ?)";
    private static final String INSERT_CLIENTS_QUERY = "INSERT INTO clients (clientId, name, lastName, phone) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE clients SET name = ?, lastName = ?, hisTrainerId = ?, phone = ? WHERE clientId = ?";
    private static final String DELETE_CLIENTS_QUERY = "UPDATE clients SET active = false WHERE clientId = ?";
    private static final String DELETE_USERS_QUERY = "UPDATE users SET active = false WHERE id = ?";
    private static final String FIND_QUERY = "SELECT clientId, name, lastName, registerDate, hisTrainerId, discount, phone, active FROM clients WHERE clientId = ?";
    private static final String FIND_ALL_ACTIVE_QUERY = "SELECT clientId, name, lastName, registerDate, hisTrainerId, discount, phone, active FROM clients WHERE active = true";
    private static final String FIND_ALL_ACTIVE_BY_TRAINER_ID_QUERY = "SELECT clientId, name, lastName, registerDate, hisTrainerId, discount, phone, active FROM clients WHERE active = true AND hisTrainerId = ?";
    private static final String FIND_ALL_QUERY = "SELECT clientId, name, lastName, registerDate, hisTrainerId, discount, phone, active FROM clients";
    private static final String FIND_ALL_BY_NAME_QUERY = "SELECT clientId, name, lastName, registerDate, hisTrainerId, discount, phone, active FROM clients WHERE name = ?";
    private static final String FIND_ALL_BY_LAST_NAME_QUERY = "SELECT clientId, name, lastName, registerDate, hisTrainerId, discount, phone, active FROM clients WHERE lastName = ?";

    private static ClientDao clientDao;

    private ClientDaoImpl() {
    }

    public static ClientDao getInstance() {
        if (clientDao == null) {
            clientDao = new ClientDaoImpl();
            logger.debug("ClientDao created");
        }
        return clientDao;
    }

    @Override
    public Client create(Client client) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement usersStatement = connection.prepareStatement(INSERT_USERS_QUERY, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement trainerStatement = connection.prepareStatement(INSERT_CLIENTS_QUERY)) {
            try {
                connection.setAutoCommit(false);
                usersStatement.setString(1, client.getLogin());
                usersStatement.setString(2, client.getPassword());
                usersStatement.setString(3, client.getRole().name());
                usersStatement.execute();

                ResultSet resultSet = usersStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    int clientId = resultSet.getInt(1);
                    client.setId(clientId);
                }

                trainerStatement.setInt(1, client.getId());
                trainerStatement.setString(2, client.getName());
                trainerStatement.setString(3, client.getLastName());
                trainerStatement.setString(4, client.getPhone());

                usersStatement.execute();

                connection.commit();
                logger.debug("Client created = {}", client);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return client;
    }

    @Override
    public boolean update(Client client) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getLastName());
            if (client.getTrainerId() != null) {
                statement.setInt(3, client.getTrainerId());
            } else {
                statement.setNull(3, MysqlType.NULL.getJdbcType());
            }
            if (client.getPhone() != null) {
                statement.setString(4, client.getPhone());
            } else {
                statement.setNull(4, MysqlType.NULL.getJdbcType());
            }
            statement.setInt(5, client.getId());

            isUpdated = statement.execute();
            logger.debug("Client updated, new trainer - {}", client);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean delete(int clientId) throws DaoException {
        boolean isDeleted;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement clientsStatement = connection.prepareStatement(DELETE_CLIENTS_QUERY);
             PreparedStatement usersStatement = connection.prepareStatement(DELETE_USERS_QUERY)) {
            try {
                connection.setAutoCommit(false);
                clientsStatement.setInt(1, clientId);
                usersStatement.setInt(1, clientId);
                isDeleted = clientsStatement.execute() & usersStatement.execute();

                if (isDeleted) {
                    logger.debug("Client deleted, id - {}", clientId);
                    connection.commit();
                } else {
                    logger.debug("Unsuccessful client delete, id - {}", clientId);
                    connection.rollback();
                }
            } finally {
                connection.setAutoCommit(true);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isDeleted;
    }

    @Override
    public Client find(int clientId) throws DaoException {
        Client client = null;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_QUERY)) {
            statement.setInt(1, clientId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.first()) {
                client = getClientFromResultSet(resultSet);
                logger.debug("Find, client - {}", client);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return client;
    }

    @Override
    public List<Client> findAllActive() throws DaoException {
        List<Client> result = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ACTIVE_QUERY)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Client client = getClientFromResultSet(resultSet);
                result.add(client);
            }
            logger.debug("FindAllActive, clients - {}", result);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public List<Client> findAllActiveByTrainerId(int trainerId) throws DaoException {
        List<Client> result = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ACTIVE_BY_TRAINER_ID_QUERY)) {
            statement.setInt(1, trainerId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Client client = getClientFromResultSet(resultSet);
                result.add(client);
            }
            logger.debug("FindAllActiveByTrainerId, clients - {}", result);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public List<Client> findAll() throws DaoException {
        List<Client> result = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Client client = getClientFromResultSet(resultSet);
                result.add(client);
            }
            logger.debug("FindAll, clients - {}", result);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public List<Client> findAllActiveByName(String name) throws DaoException {
        List<Client> result = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_BY_NAME_QUERY)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Client client = getClientFromResultSet(resultSet);
                result.add(client);
            }
            logger.debug("FindAllActiveByName, clients - {}", result);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public List<Client> findAllActiveByLastName(String lastName) throws DaoException {
        List<Client> result = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_BY_LAST_NAME_QUERY)) {
            statement.setString(1, lastName);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Client client = getClientFromResultSet(resultSet);
                result.add(client);
            }
            logger.debug("FindAllActiveByLastName, clients - {}", result);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    private Client getClientFromResultSet(ResultSet resultSet) throws SQLException {
        Client client = new Client();
        client.setId(resultSet.getInt(TableColumn.CLIENT_ID));
        client.setName(resultSet.getString(TableColumn.CLIENT_NAME));
        client.setLastName(resultSet.getString(TableColumn.CLIENT_LAST_NAME));
        client.setRegisterDateTime(resultSet.getTimestamp(TableColumn.CLIENT_REGISTER_DATE).toLocalDateTime());
        client.setTrainerId(resultSet.getInt(TableColumn.CLIENT_HIS_TRAINER_ID));
        client.setActive(resultSet.getBoolean(TableColumn.CLIENT_ACTIVE));
        client.setDiscount(resultSet.getInt(TableColumn.CLIENT_DISCOUNT));
        client.setPhone(resultSet.getString(TableColumn.CLIENT_PHONE));
        return client;
    }

}
