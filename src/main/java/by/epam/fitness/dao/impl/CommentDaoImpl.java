package by.epam.fitness.dao.impl;

import by.epam.fitness.dao.CommentDao;
import by.epam.fitness.dao.TableColumn;
import by.epam.fitness.exception.DaoException;
import by.epam.fitness.model.Comment;
import by.epam.fitness.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl implements CommentDao {
    private static Logger logger = LogManager.getLogger(CommentDaoImpl.class);

    private static final String INSERT_QUERY = "INSERT INTO comments (clientId, trainerId, comment) VALUES (?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE comments SET clientId = ?, trainerId = ?, comment = ? WHERE id = ?";
    private static final String DELETE_QUERY = "UPDATE comments SET active = false WHERE id = ?";
    private static final String FIND_QUERY = "SELECT id, clientId, trainerId, registerDate, comment, active FROM comments WHERE id = ?";
    private static final String FIND_ACTIVE_QUERY = "SELECT id, clientId, trainerId, registerDate, comment, active FROM comments WHERE id = ? AND active = true";
    private static final String FIND_ALL_ACTIVE_QUERY = "SELECT id, clientId, trainerId, registerDate, comment, active FROM comments WHERE active = true";
    private static final String FIND_ALL_ACTIVE_BY_TRAINER_QUERY = "SELECT id, clientId, trainerId, registerDate, comment, active FROM comments WHERE trainerId = ? AND active = true";
    private static final String FIND_ALL_QUERY = "SELECT id, clientId, trainerId, registerDate, comment, active FROM comments";

    private static CommentDao commentDao;

    private CommentDaoImpl() {
    }

    public static CommentDao getInstance() {
        if (commentDao == null) {
            commentDao = new CommentDaoImpl();
            logger.debug("CommentDao created");
        }
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
            statement.setInt(1, comment.getClientId());
            statement.setInt(2, comment.getTrainerId());
            statement.setString(3, comment.getComment());

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
    public Comment findActive(int commentId) throws DaoException {
        Comment comment = null;

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ACTIVE_QUERY)) {
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
                Comment assignment = getCommentFromResultSet(resultSet);
                comments.add(assignment);
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
                Comment assignment = getCommentFromResultSet(resultSet);
                comments.add(assignment);
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
                Comment assignment = getCommentFromResultSet(resultSet);
                comments.add(assignment);
            }
            logger.debug("FindAllActive comments - {}", comments);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return comments;
    }

    private Comment getCommentFromResultSet(ResultSet resultSet) throws SQLException {
        Comment comment = new Comment();
        comment.setId(resultSet.getInt(TableColumn.COMMENT_ID));
        comment.setClientId(resultSet.getInt(TableColumn.COMMENT_CLIENT_ID));
        comment.setTrainerId(resultSet.getInt(TableColumn.COMMENT_TRAINER_ID));
        comment.setRegisterDate(resultSet.getTimestamp(TableColumn.COMMENT_REGISTER_DATE).toLocalDateTime());
        comment.setComment(resultSet.getString(TableColumn.COMMENT_COMMENT));
        comment.setActive(resultSet.getBoolean(TableColumn.COMMENT_ACTIVE));
        return comment;
    }
}
