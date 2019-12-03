package by.epam.fitness.service.impl;

import by.epam.fitness.dao.CommentDao;
import by.epam.fitness.dao.impl.CommentDaoImpl;
import by.epam.fitness.exception.DaoException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.Comment;
import by.epam.fitness.service.CommentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The type Comment service.
 */
public class CommentServiceImpl implements CommentService {
    private static Logger logger = LogManager.getLogger(CommentServiceImpl.class);
    private static CommentService commentService = new CommentServiceImpl();

    private CommentDao commentDao = CommentDaoImpl.getInstance();

    private CommentServiceImpl() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static CommentService getInstance() {
        return commentService;
    }

    @Override
    public Comment create(Comment comment) throws ServiceException {
        logger.trace("In service method create comment.");
        Comment createdComment;
        try {
            createdComment = commentDao.create(comment);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return createdComment;
    }

    @Override
    public boolean update(Comment comment) throws ServiceException {
        logger.trace("In service method update comment.");
        boolean isUpdated;
        try {
            isUpdated = commentDao.update(comment);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean delete(int commentId) throws ServiceException {
        logger.trace("In service method delete comment.");
        boolean isDeleted;
        try {
            isDeleted = commentDao.delete(commentId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return isDeleted;
    }

    @Override
    public Comment find(int commentId) throws ServiceException {
        logger.trace("In service method find comment.");
        Comment comment;
        try {
            comment = commentDao.find(commentId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return comment;
    }

    @Override
    public List<Comment> findAllByFilter(Comment comment) throws ServiceException {
        logger.trace("In service method findAllByFilter.");
        List<Comment> comments;
        try {
            comments = commentDao.findAllByFilter(comment);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return comments;
    }

    @Override
    public List<Comment> findAllActive() throws ServiceException {
        logger.trace("In service method findAllActive.");
        List<Comment> comments;
        try {
            comments = commentDao.findAllActive();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return comments;
    }

    @Override
    public List<Comment> findAll() throws ServiceException {
        logger.trace("In service method findAll comments.");
        List<Comment> comments;
        try {
            comments = commentDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return comments;
    }


    @Override
    public List<Comment> findAllActiveLimit(int page) throws ServiceException {
        logger.trace("In service method findAllActiveLimit.");
        int from = 5 * (page - 1);
        int numberOfLimits = 5;
        List<Comment> comments;
        try {
            comments = commentDao.findAllActiveLimit(from, numberOfLimits);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return comments;
    }

    @Override
    public int countAll(Boolean active) throws ServiceException {
        logger.trace("In service method countAll comments.");
        int count;
        try {
            count = commentDao.countAll(active);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return count;
    }
}
