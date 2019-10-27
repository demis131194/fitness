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

    private static final String INSERT_QUERY = "INSERT INTO comments (userId, trainerId, comment) VALUES (?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE comments SET userId = ?, trainerId = ?, comment = ? WHERE id = ?";
    private static final String DELETE_QUERY = "UPDATE comments SET active = false WHERE id = ?";
    private static final String FIND_ACTIVE_QUERY = "SELECT id, userId, trainerId, registerDate, comment, active FROM comments WHERE id = ? AND active = true";
    private static final String FIND_ALL_ACTIVE_QUERY = "SELECT id, userId, trainerId, registerDate, comment, active FROM comments WHERE active = true";
    private static final String FIND_ALL_ACTIVE_BY_TRAINER_QUERY = "SELECT id, userId, trainerId, registerDate, comment, active FROM comments WHERE trainerId = ? AND active = true";
    private static final String FIND_ALL_QUERY = "SELECT id, userId, trainerId, registerDate, comment, active FROM comments";

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
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            statement.setInt(1, comment.getUserId());
            statement.setInt(2, comment.getTrainerId());
            statement.setString(3, comment.getComment());
            statement.execute();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int orderId = resultSet.getInt(1);
                comment.setId(orderId);
            }

            connection.commit();
            connection.setAutoCommit(true);
            createdComment = comment;
            logger.debug("Assignment created = {}", comment);

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.warn(e);
            }
        }
        return createdComment;
    }

    @Override
    public boolean update(Comment comment) throws DaoException {
        boolean isUpdated;
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setInt(1, comment.getUserId());
            statement.setInt(2, comment.getTrainerId());
            statement.setString(3, comment.getComment());

            isUpdated = statement.execute();
            logger.debug("Comment updated, new comment - {}", comment);

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
    public boolean delete(int commentId) throws DaoException {
        boolean isDeleted;
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, commentId);
            isDeleted = statement.execute();

            if (isDeleted) {
                logger.debug("Comment deleted, id - {}", commentId);
            } else {
                logger.debug("Unsuccessful comment delete, id - {}", commentId);
            }

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
    public Comment findActive(int commentId) throws DaoException {
        Comment comment = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement statement = connection.prepareStatement(FIND_ACTIVE_QUERY)) {
            statement.setInt(1, commentId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.first()) {
                comment = getCommentFromResultSet(resultSet);
                logger.debug("FindActive comment - {}", comment);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.warn(e);
            }

        }
        return comment;
    }

    @Override
    public List<Comment> findAllActive() throws DaoException {
        List<Comment> comments = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_ACTIVE_QUERY)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Comment assignment = getCommentFromResultSet(resultSet);
                comments.add(assignment);
            }
            logger.debug("FindAllActive comments - {}", comments);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.warn(e);
            }

        }
        return comments;
    }

    @Override
    public List<Comment> findAllActiveByTrainer(int trainerId) throws DaoException {
        List<Comment> comments = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_ACTIVE_BY_TRAINER_QUERY)) {
            statement.setInt(1, trainerId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Comment assignment = getCommentFromResultSet(resultSet);
                comments.add(assignment);
            }
            logger.debug("FindAllActiveByTrainer comments - {}", comments);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.warn(e);
            }

        }
        return comments;
    }

    @Override
    public List<Comment> findAll() throws DaoException {
        List<Comment> comments = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUERY)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Comment assignment = getCommentFromResultSet(resultSet);
                comments.add(assignment);
            }
            logger.debug("FindAllActive comments - {}", comments);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.warn(e);
            }

        }
        return comments;
    }

    private Comment getCommentFromResultSet(ResultSet resultSet) throws SQLException {
        Comment comment = new Comment();
        comment.setId(resultSet.getInt(TableColumn.COMMENT_ID));
        comment.setUserId(resultSet.getInt(TableColumn.COMMENT_USER_ID));
        comment.setTrainerId(resultSet.getInt(TableColumn.COMMENT_TRAINER_ID));
        comment.setRegisterDate(resultSet.getTimestamp(TableColumn.COMMENT_REGISTER_DATE).toLocalDateTime());
        comment.setComment(resultSet.getString(TableColumn.COMMENT_COMMENT));
        comment.setActive(resultSet.getBoolean(TableColumn.COMMENT_ACTIVE));
        return comment;
    }
}
