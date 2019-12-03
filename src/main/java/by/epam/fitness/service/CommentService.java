package by.epam.fitness.service;

import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.Comment;

import java.util.List;

/**
 * The interface Comment service.
 */
public interface CommentService {

    /**
     * Create comment.
     *
     * @param comment the comment
     * @return the comment
     * @throws ServiceException the service exception
     */
    Comment create(Comment comment) throws ServiceException;

    /**
     * Update boolean.
     *
     * @param comment the comment
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean update(Comment comment) throws ServiceException;

    /**
     * Delete boolean.
     *
     * @param commentId the comment id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean delete(int commentId) throws ServiceException;

    /**
     * Find comment.
     *
     * @param commentId the comment id
     * @return the comment
     * @throws ServiceException the service exception
     */
    Comment find(int commentId) throws ServiceException;

    /**
     * Find all by filter list.
     *
     * @param comment the comment
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Comment> findAllByFilter(Comment comment) throws ServiceException;

    /**
     * Find all active list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Comment> findAllActive() throws ServiceException;

    /**
     * Find all list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Comment> findAll() throws ServiceException;

    /**
     * Find all active limit list.
     *
     * @param page the page
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Comment> findAllActiveLimit(int page) throws ServiceException;

    /**
     * Count all int.
     *
     * @param active the active
     * @return the int
     * @throws ServiceException the service exception
     */
    int countAll(Boolean active) throws ServiceException;
}
