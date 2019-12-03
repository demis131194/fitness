package by.epam.fitness.service;

import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.Comment;

import java.util.List;

public interface CommentService {

    Comment create(Comment comment) throws ServiceException;
    boolean update(Comment comment) throws ServiceException;
    boolean delete(int commentId) throws ServiceException;
    Comment find(int commentId) throws ServiceException;
    List<Comment> findAllByFilter(Comment comment) throws ServiceException;
    List<Comment> findAllActive() throws ServiceException;
    List<Comment> findAllActiveByTrainer(int trainerId) throws ServiceException;
    List<Comment> findAll() throws ServiceException;

    List<Comment> findAllActiveLimit(int page) throws ServiceException;
    List<Comment> findAllByFilterLimit(Comment filter, int page) throws ServiceException;
    List<Comment> findAllActiveByTrainerLimit(int trainerId, int page) throws ServiceException;
    List<Comment> findAllLimit(int page) throws ServiceException;
    int countAll(Boolean active) throws ServiceException;
}
