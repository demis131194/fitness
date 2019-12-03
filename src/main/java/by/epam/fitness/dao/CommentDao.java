package by.epam.fitness.dao;

import by.epam.fitness.exception.DaoException;
import by.epam.fitness.model.Comment;

import java.util.List;

/**
 * The interface Comment dao.
 */
public interface CommentDao {
    /**
     * Create comment.
     *
     * @param comment the comment
     * @return the comment
     * @throws DaoException the dao exception
     */
    Comment create(Comment comment) throws DaoException;

    /**
     * Update boolean.
     *
     * @param comment the comment
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean update(Comment comment) throws DaoException;

    /**
     * Delete boolean.
     *
     * @param commentId the comment id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean delete(int commentId) throws DaoException;

    /**
     * Find comment.
     *
     * @param commentId the comment id
     * @return the comment
     * @throws DaoException the dao exception
     */
    Comment find(int commentId) throws DaoException;

    /**
     * Find all active list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Comment> findAllActive() throws DaoException;

    /**
     * Find all by filter list.
     *
     * @param filter the filter
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Comment> findAllByFilter(Comment filter) throws DaoException;

    /**
     * Find all list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Comment> findAll() throws DaoException;

    /**
     * Find all active limit list.
     *
     * @param from          the from
     * @param numberOfLines the number of lines
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Comment> findAllActiveLimit(int from, int numberOfLines) throws DaoException;

    /**
     * Count all int.
     *
     * @param active the active
     * @return the int
     * @throws DaoException the dao exception
     */
    int countAll(Boolean active) throws DaoException;

}
