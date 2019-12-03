package by.epam.fitness.dao;

import by.epam.fitness.exception.DaoException;
import by.epam.fitness.model.Comment;

import java.util.List;

public interface CommentDao {
    Comment create(Comment comment) throws DaoException;
    boolean update(Comment comment) throws DaoException;
    boolean delete(int commentId) throws DaoException;
    Comment find(int commentId) throws DaoException;
    List<Comment> findAllActive() throws DaoException;
    List<Comment> findAllByFilter(Comment comment) throws DaoException;
    List<Comment> findAllActiveByTrainer(int trainerId) throws DaoException;
    List<Comment> findAll() throws DaoException;

//    List<Comment> findAllActiveLimit() throws DaoException;
//    List<Comment> findAllByFilterLimit(Comment comment) throws DaoException;
//    List<Comment> findAllActiveByTrainerLimit(int trainerId) throws DaoException;
//    List<Comment> findAllLimit() throws DaoException;

}
