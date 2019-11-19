package by.epam.fitness.dao.impl;

import by.epam.fitness.dao.CommentDao;
import by.epam.fitness.dao.TableColumnName;
import by.epam.fitness.exception.DaoException;
import by.epam.fitness.model.Comment;
import by.epam.fitness.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommentDaoImpl implements CommentDao {
    private static Logger logger = LogManager.getLogger(CommentDaoImpl.class);

    private static final String INSERT_QUERY = "INSERT INTO comments (clientId, trainerId, comment) VALUES (?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE comments SET clientId = IFNULL(?, clientId), trainerId = IFNULL(?, trainerId), comment = IFNULL(?, comment), active = IFNULL(?, active) WHERE id = ?";
    private static final String DELETE_QUERY = "UPDATE comments SET active = false WHERE id = ?";
    private static final String FIND_QUERY = "SELECT comments.id, comments.clientId, c.name AS clientName, c.lastName AS clientLastName, comments.trainerId, t.name AS trainerName, t.lastName AS trainerLastName, comments.registerDate, comments.comment, comments.active  FROM comments \n" +
            "LEFT JOIN clients c on comments.clientId = c.clientId\n" +
            "LEFT JOIN trainers t ON comments.trainerId = t.trainerId " +
            "WHERE comments.id = ?";
    private static final String FIND_ALL_BY_FILTER_QUERY = "SELECT comments.id, comments.clientId, c.name AS clientName, c.lastName AS clientLastName, comments.trainerId, t.name AS trainerName, t.lastName AS trainerLastName, comments.registerDate, comments.comment, comments.active  FROM comments \n" +
            "LEFT JOIN clients c on comments.clientId = c.clientId\n" +
            "LEFT JOIN trainers t ON comments.trainerId = t.trainerId " +
            "WHERE c.name = IFNULL(?, c.name) AND c.lastName = IFNULL(?, c.lastName) AND t.name = IFNULL(?, t.name) AND t.lastName = IFNULL(?, t.lastName) AND CAST(comments.registerDate AS DATE) = IFNULL(?, CAST(comments.registerDate AS DATE)) AND comments.active = IFNULL(?, comments.active)";
    private static final String FIND_ALL_ACTIVE_QUERY = "SELECT comments.id, comments.clientId, c.name AS clientName, c.lastName AS clientLastName, comments.trainerId, t.name AS trainerName, t.lastName AS trainerLastName, comments.registerDate, comments.comment, comments.active  FROM comments \n" +
            "LEFT JOIN clients c on comments.clientId = c.clientId\n" +
            "LEFT JOIN trainers t ON comments.trainerId = t.trainerId " +
            "WHERE comments.active = true";
    private static final String FIND_ALL_ACTIVE_BY_TRAINER_QUERY = "SELECT comments.id, comments.clientId, c.name AS clientName, c.lastName AS clientLastName, comments.trainerId, t.name AS trainerName, t.lastName AS trainerLastName, comments.registerDate, comments.comment, comments.active  FROM comments \n" +
            "LEFT JOIN clients c on comments.clientId = c.clientId\n" +
            "LEFT JOIN trainers t ON comments.trainerId = t.trainerId " +
            "WHERE t.trainerId = ?";
    private static final String FIND_ALL_QUERY = "SELECT comments.id, comments.clientId, c.name AS clientName, c.lastName AS clientLastName, comments.trainerId, t.name AS trainerName, t.lastName AS trainerLastName, comments.registerDate, comments.comment, comments.active  FROM comments \n" +
            "LEFT JOIN clients c on comments.clientId = c.clientId\n" +
            "LEFT JOIN trainers t ON comments.trainerId = t.trainerId";

    private static CommentDao commentDao = new CommentDaoImpl();

    private CommentDaoImpl() {
    }

    public static CommentDao getInstance() {
        return commentDao;
    }

    @Override
    public Comment create(Comment comment) throws DaoException {
        Comment createdComment;

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, comment.getClientId());
            statement.setInt(2, comment.getTrainerId());
            statement.setString(3, comment.getComment());
            statement.execute();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int orderId = resultSet.getInt(1);
                comment.setId(orderId);
            }

            createdComment = comment;
            logger.debug("Assignment created = {}", comment);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return createdComment;
    }

    @Override
    public boolean update(Comment comment) throws DaoException {
        boolean isUpdated;

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            if (comment.getClientId() != null) {
                statement.setInt(1, comment.getClientId());
            } else {
                statement.setNull(1, Types.INTEGER);
            }
            if (comment.getTrainerId() != null) {
                statement.setInt(2, comment.getTrainerId());
            } else {
                statement.setNull(2, Types.INTEGER);
            }
            if (comment.getComment() != null) {
                statement.setString(3, comment.getComment());
            } else {
                statement.setNull(3, Types.VARCHAR);
            }
            if (comment.getActive() != null) {
                statement.setBoolean(4, comment.getActive());;
            } else {
                statement.setNull(4, Types.BOOLEAN);
            }
            statement.setInt(5, comment.getId());

            isUpdated = statement.execute();
            logger.debug("Comment updated, new comment - {}", comment);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean delete(int commentId) throws DaoException {
        boolean isDeleted;

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, commentId);
            isDeleted = statement.execute();

            if (isDeleted) {
                logger.debug("Comment deleted, id - {}", commentId);
            } else {
                logger.debug("Unsuccessful comment delete, id - {}", commentId);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isDeleted;
    }

    @Override
    public Comment find(int commentId) throws DaoException {
        Comment comment = null;

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_QUERY)) {
            statement.setInt(1, commentId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.first()) {
                comment = getCommentFromResultSet(resultSet);
                logger.debug("FindActive comment - {}", comment);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return comment;
    }

    @Override
    public List<Comment> findAllActive() throws DaoException {
        List<Comment> comments = new ArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ACTIVE_QUERY)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Comment comment = getCommentFromResultSet(resultSet);
                comments.add(comment);
            }
            logger.debug("FindAllActive comments - {}", comments);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return comments;
    }

    @Override
    public List<Comment> findAllByFilter(Comment filter) throws DaoException {
        List<Comment> comments = new ArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_BY_FILTER_QUERY)) {
            if (filter.getClientName() != null) {
                statement.setString(1, filter.getClientName());
            } else {
                statement.setNull(1, Types.VARCHAR);
            }
            if (filter.getClientLastName() != null) {
                statement.setString(2, filter.getClientLastName());
            } else {
                statement.setNull(2, Types.VARCHAR);
            }
            if (filter.getTrainerName() != null) {
                statement.setString(3, filter.getTrainerName());
            } else {
                statement.setNull(3, Types.VARCHAR);
            }
            if (filter.getTrainerLastName() != null) {
                statement.setString(4, filter.getTrainerLastName());
            } else {
                statement.setNull(4, Types.VARCHAR);
            }
            if (filter.getRegisterDate() != null) {
                statement.setDate(5, Date.valueOf(filter.getRegisterDate().toLocalDate()));
            } else {
                statement.setNull(5, Types.DATE);
            }
            if (filter.getActive() != null) {
                statement.setBoolean(6, filter.getActive());
            } else {
                statement.setNull(6, Types.INTEGER);
            }
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Comment comment = getCommentFromResultSet(resultSet);
                comments.add(comment);
            }
            logger.debug("FindAllActive comments - {}", comments);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return comments;
    }

    @Override
    public List<Comment> findAllActiveByTrainer(int trainerId) throws DaoException {
        List<Comment> comments = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ACTIVE_BY_TRAINER_QUERY)) {
            statement.setInt(1, trainerId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Comment comment = getCommentFromResultSet(resultSet);
                comments.add(comment);
            }
            logger.debug("FindAllActiveByTrainer comments - {}", comments);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return comments;
    }

    @Override
    public List<Comment> findAll() throws DaoException {
        List<Comment> comments = new ArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUERY)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Comment comment = getCommentFromResultSet(resultSet);
                comments.add(comment);
            }
            logger.debug("FindAllActive comments - {}", comments);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return comments;
    }

    private Comment getCommentFromResultSet(ResultSet resultSet) throws SQLException {
        Comment comment = new Comment();
        comment.setId(resultSet.getInt(TableColumnName.COMMENT_ID));
        comment.setClientId(resultSet.getInt(TableColumnName.COMMENT_CLIENT_ID));
        comment.setClientName(resultSet.getString(TableColumnName.COMMENT_CLIENT_NAME));
        comment.setClientLastName(resultSet.getString(TableColumnName.COMMENT_CLIENT_LAST_NAME));
        comment.setTrainerId(resultSet.getInt(TableColumnName.COMMENT_TRAINER_ID));
        comment.setTrainerName(resultSet.getString(TableColumnName.COMMENT_TRAINER_NAME));
        comment.setTrainerLastName(resultSet.getString(TableColumnName.COMMENT_TRAINER_LAST_NAME));
        comment.setRegisterDate(resultSet.getTimestamp(TableColumnName.COMMENT_REGISTER_DATE).toLocalDateTime());
        comment.setComment(resultSet.getString(TableColumnName.COMMENT_COMMENT));
        comment.setActive(resultSet.getBoolean(TableColumnName.COMMENT_ACTIVE));
        return comment;
    }
}
