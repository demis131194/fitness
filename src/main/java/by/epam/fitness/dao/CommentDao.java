package by.epam.fitness.dao;

import by.epam.fitness.exception.DaoException;
import by.epam.fitness.model.Comment;
import by.epam.fitness.to.CommentTo;

import java.util.List;

public interface CommentDao {
    Comment create(Comment comment) throws DaoException;
    boolean update(Comment comment) throws DaoException;
    boolean delete(int commentId) throws DaoException;
    CommentTo find(int commentId) throws DaoException;
    List<CommentTo> findAllActive() throws DaoException;
    List<CommentTo> findAllActiveByTrainer(int trainerId) throws DaoException;
    List<CommentTo> findAll() throws DaoException;
}
