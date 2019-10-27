package by.epam.fitness.dao;

import by.epam.fitness.exception.DaoException;
import by.epam.fitness.model.Comment;

import java.util.List;

public interface CommentDao {
    Comment create(Comment comment) throws DaoException;
    boolean update(Comment comment) throws DaoException;
    boolean delete(int commentId) throws DaoException;
    Comment findActive(int commentId) throws DaoException;
    List<Comment> findAllActive() throws DaoException;
    List<Comment> findAll() throws DaoException;
}
