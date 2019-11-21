package by.epam.fitness.dao.impl;

import by.epam.fitness.dao.TableColumnName;
import by.epam.fitness.dao.ClientDao;
import by.epam.fitness.exception.DaoException;
import by.epam.fitness.model.user.Client;
import by.epam.fitness.model.user.UserRole;
import by.epam.fitness.pool.ConnectionPool;
;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClientDaoImpl implements ClientDao {
    private static Logger logger = LogManager.getLogger(ClientDaoImpl.class);

    private static final String INSERT_USERS_QUERY = "INSERT INTO users (login, password, role) VALUES (?, ?, ?)";
    private static final String INSERT_CLIENTS_QUERY = "INSERT INTO clients (clientId, name, lastName, phone) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE clients SET name = IFNULL(?, name), lastName = IFNULL(?, lastName), phone = IFNULL(?, phone), discount = IFNULL(?, discount), discountLevel = IFNULL(?, discountLevel) WHERE clientId = ?";
    private static final String UPDATE_CLIENT_CASH_QUERY = "UPDATE clients SET cash = cash + ? WHERE clientId = ?";
    private static final String FIND_CARD_QUERY = "SELECT cards.account FROM cards WHERE cardNumber = ?";
    private static final String UPDATE_CARD_QUERY = "SELECT cards.account FROM cards WHERE cardNumber = ?";
    private static final String FIND_QUERY = "SELECT clientId, name, lastName, registerDate, discount, phone, cash, discountLevel, active FROM clients WHERE clientId = ?";
    private static final String FIND_ALL_ACTIVE_QUERY = "SELECT clientId, name, lastName, registerDate, discount, phone, cash, discountLevel, active FROM clients WHERE active = true";
    private static final String FIND_ALL_QUERY = "SELECT clientId, name, lastName, registerDate, discount, phone, cash, discountLevel, active FROM clients";
    private static final String FIND_ALL_BY_NAME_AND_LAST_NAME_QUERY = "SELECT clientId, name, lastName, registerDate, discount, phone, cash, discountLevel, active FROM clients WHERE name = IFNULL(?, name) AND lastName = IFNULL(?, lastName)";

    private static ClientDao clientDao = new ClientDaoImpl();

    private ClientDaoImpl() {
    }

    public static ClientDao getInstance() {
        return clientDao;
    }

    @Override
    public Client create(Client client) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement usersStatement = connection.prepareStatement(INSERT_USERS_QUERY, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement clientStatement = connection.prepareStatement(INSERT_CLIENTS_QUERY)) {
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

                clientStatement.setInt(1, client.getId());
                clientStatement.setString(2, client.getName());
                clientStatement.setString(3, client.getLastName());
                if (client.getPhone() != null) {
                    clientStatement.setString(4, client.getPhone());
                } else {
                    clientStatement.setNull(4, Types.NULL);
                }

                clientStatement.execute();

                connection.commit();
                logger.debug("Client created = {}", client);
            } catch (SQLException e) {
                connection.rollback();
                throw new SQLException(e);
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
            if (client.getName() != null) {
                statement.setString(1, client.getName());
            } else {
                statement.setNull(1, Types.VARCHAR);
            }
            if (client.getLastName() != null) {
                statement.setString(2, client.getLastName());
            } else {
                statement.setNull(2, Types.VARCHAR);
            }
            if (client.getPhone() != null) {
                statement.setString(3, client.getPhone());
            } else {
                statement.setNull(3, Types.VARCHAR);
            }
            if (client.getDiscount() != null) {
                statement.setInt(4, client.getDiscount());
            } else {
                statement.setNull(4, Types.INTEGER);
            }
            if (client.getDiscountLevel() != null) {
                statement.setInt(5, client.getDiscountLevel());
            } else {
                statement.setNull(5, Types.INTEGER);
            }
            statement.setInt(6, client.getId());

            isUpdated = statement.executeUpdate() == 1;
            logger.debug("Client updated, new client - {}", client);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean updateCash(int clientId, BigDecimal cash, String card) throws DaoException {
        boolean isUpdated = false;
        //TODO
//        try (Connection connection = ConnectionPool.getInstance().takeConnection();
//             PreparedStatement cardAmountStatement = connection.prepareStatement(FIND_CARD_QUERY);
//             PreparedStatement updateCardStatement = connection.prepareStatement(UPDATE_CARD_QUERY);
//             PreparedStatement clientStatement = connection.prepareStatement(UPDATE_CLIENT_CASH_QUERY)) {
//
//            try {
//                connection.setAutoCommit(false);
//
//                cardAmountStatement.setString(1, card);
//
//                ResultSet resultSet = cardAmountStatement.executeQuery();
//
//                if (resultSet.first()) {
//                    BigDecimal cardAmount = resultSet.getBigDecimal(1);
//
//                }
//
//                cardAmountStatement.setBigDecimal(1, Objects.requireNonNull(cash));
//                cardAmountStatement.setInt(2, clientId);
//
//                if (client.getPhone() != null) {
//                    clientStatement.setString(4, client.getPhone());
//                } else {
//                    clientStatement.setNull(4, Types.NULL);
//                }
//
//                clientStatement.execute();
//
//                connection.commit();
//                logger.debug("Client cash updated, value = {}", client);
//            } catch (SQLException e) {
//                connection.rollback();
//                throw new SQLException(e);
//            } finally {
//                connection.setAutoCommit(true);
//            }
//        } catch (SQLException e) {
//            throw new DaoException(e);
//        }
        return isUpdated;
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
    public List<Client> findAllActiveByNameAndLastName(String name, String lastName) throws DaoException {
        List<Client> result = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_BY_NAME_AND_LAST_NAME_QUERY)) {
            if (name != null) {
                statement.setString(1, name);
            } else {
                statement.setNull(1, Types.VARCHAR);
            }
            if (lastName != null) {
                statement.setString(2, lastName);
            } else {
                statement.setNull(2, Types.VARCHAR);
            }

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

    private Client getClientFromResultSet(ResultSet resultSet) throws SQLException {
        Client client = new Client();
        client.setId(resultSet.getInt(TableColumnName.CLIENT_ID));
        client.setName(resultSet.getString(TableColumnName.CLIENT_NAME));
        client.setLastName(resultSet.getString(TableColumnName.CLIENT_LAST_NAME));
        client.setRegisterDateTime(resultSet.getTimestamp(TableColumnName.CLIENT_REGISTER_DATE).toLocalDateTime());
        client.setDiscount(resultSet.getInt(TableColumnName.CLIENT_DISCOUNT));
        client.setPhone(resultSet.getString(TableColumnName.CLIENT_PHONE));
        client.setCash(resultSet.getBigDecimal(TableColumnName.CLIENT_CASH));
        client.setDiscountLevel(resultSet.getInt(TableColumnName.CLIENT_DISCOUNT_LEVEL));
        client.setActive(resultSet.getBoolean(TableColumnName.CLIENT_ACTIVE));
        client.setRole(UserRole.CLIENT);
        return client;
    }

}
