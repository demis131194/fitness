package by.epam.fitness.dao.impl;

import by.epam.fitness.dao.TableColumnName;
import by.epam.fitness.dao.TrainerDao;
import by.epam.fitness.exception.DaoException;
import by.epam.fitness.model.user.Trainer;
import by.epam.fitness.model.user.UserRole;
import by.epam.fitness.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainerDaiImpl implements TrainerDao {
    private static Logger logger = LogManager.getLogger(TrainerDaiImpl.class);

    private static final String INSERT_USERS_QUERY = "INSERT INTO users (login, password, role) VALUES (?, ?, ?)";
    private static final String INSERT_TRAINER_QUERY = "INSERT INTO trainers (trainerId, name, lastName, phone) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE trainers SET name = IFNULL(?, name), lastName = IFNULL(?, lastName), phone = IFNULL(?, phone) WHERE trainerId = ?";
    private static final String FIND_QUERY = "SELECT trainerId, name, lastName, registerDate, phone, active FROM trainers WHERE trainerId = ?";
    private static final String FIND_ALL_ACTIVE_QUERY = "SELECT trainerId, name, lastName, registerDate, phone, active FROM trainers WHERE active = true";
    private static final String FIND_ALL_QUERY = "SELECT trainerId, name, lastName, registerDate, phone, active FROM trainers";
    private static final String FIND_ALL_ACTIVE_BY_NAME_AND_LAST_NAME_QUERY = "SELECT trainerId, name, lastName, registerDate, phone, active FROM trainers WHERE name = IFNULL(?, name) AND lastName = IFNULL(?, lastName)";

    private static TrainerDao adminDao = new TrainerDaiImpl();

    private TrainerDaiImpl() {
    }

    public static TrainerDao getInstance() {
        return adminDao;
    }

    @Override
    public Trainer create(Trainer trainer) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement usersStatement = connection.prepareStatement(INSERT_USERS_QUERY, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement trainerStatement = connection.prepareStatement(INSERT_TRAINER_QUERY)) {
            try {
                connection.setAutoCommit(false);
                usersStatement.setString(1, trainer.getLogin());
                usersStatement.setString(2, trainer.getPassword());
                usersStatement.setString(3, trainer.getRole().name());
                usersStatement.execute();

                ResultSet resultSet = usersStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    int trainerId = resultSet.getInt(1);
                    trainer.setId(trainerId);
                }

                trainerStatement.setInt(1, trainer.getId());
                trainerStatement.setString(2, trainer.getName());
                trainerStatement.setString(3, trainer.getLastName());
                trainerStatement.setString(4, trainer.getPhone());

                usersStatement.execute();

                connection.commit();
                logger.debug("Trainer created = {}", trainer);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return trainer;
    }

    @Override
    public boolean update(Trainer trainer) throws DaoException {
        boolean isUpdated;

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            if (trainer.getName() != null) {
                statement.setString(1, trainer.getName());
            } else {
                statement.setNull(1, Types.VARCHAR);
            }
            if (trainer.getLastName() != null) {
                statement.setString(2, trainer.getLastName());
            } else {
                statement.setNull(2, Types.VARCHAR);
            }
            if (trainer.getPhone() != null) {
                statement.setString(3, trainer.getPhone());
            } else {
                statement.setNull(3, Types.VARCHAR);
            }
            statement.setInt(4, trainer.getId());

            isUpdated = statement.execute();
            logger.debug("Trainer updated, new trainer - {}", trainer);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isUpdated;
    }

    @Override
    public Trainer find(int trainerId) throws DaoException {
        Trainer trainer = null;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_QUERY)) {
            statement.setInt(1, trainerId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.first()) {
                trainer = getTrainerFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return trainer;
    }

    @Override
    public List<Trainer> findAllActive() throws DaoException {
        List<Trainer> result = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ACTIVE_QUERY)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Trainer trainer = getTrainerFromResultSet(resultSet);
                result.add(trainer);
            }

            logger.debug("FindAllActive, trainer - {}", result);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public List<Trainer> findAll() throws DaoException {
        List<Trainer> result = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Trainer trainer = getTrainerFromResultSet(resultSet);
                result.add(trainer);
            }

            logger.debug("FindAll, trainer - {}", result);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public List<Trainer> findAllActiveByNameAndLastName(String name, String lastNme) throws DaoException {
        List<Trainer> result = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ACTIVE_BY_NAME_AND_LAST_NAME_QUERY)) {
            if (name != null) {
                statement.setString(1, name);
            } else {
                statement.setNull(1, Types.VARCHAR);
            }
            if (lastNme != null) {
                statement.setString(2, lastNme);
            } else {
                statement.setNull(2, Types.VARCHAR);
            }
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Trainer trainer = getTrainerFromResultSet(resultSet);
                result.add(trainer);
            }
            logger.debug("FindAllActiveByName, trainer - {}", result);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    private Trainer getTrainerFromResultSet(ResultSet resultSet) throws SQLException {
        Trainer trainer = new Trainer();
        trainer.setId(resultSet.getInt(TableColumnName.TRAINER_ID));
        trainer.setName(resultSet.getString(TableColumnName.TRAINER_NAME));
        trainer.setLastName(resultSet.getString(TableColumnName.TRAINER_LAST_NAME));
        trainer.setRegisterDateTime(resultSet.getTimestamp(TableColumnName.TRAINER_REGISTER_DATE).toLocalDateTime());
        trainer.setPhone(resultSet.getString(TableColumnName.TRAINER_PHONE));
        trainer.setActive(resultSet.getBoolean(TableColumnName.TRAINER_ACTIVE));
        trainer.setRole(UserRole.TRAINER);
        return trainer;
    }
}
