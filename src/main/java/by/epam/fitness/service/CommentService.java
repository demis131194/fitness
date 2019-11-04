package by.epam.fitness.service;

import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.Comment;
import by.epam.fitness.to.CommentTo;

import java.util.List;

public interface CommentService {

    Comment create(Comment comment) throws ServiceException;
    boolean update(Comment comment) throws ServiceException;
    boolean delete(int commentId) throws ServiceException;
    CommentTo find(int commentId) throws ServiceException;
    List<CommentTo> findAllActive() throws ServiceException;
    List<CommentTo> findAllActiveByTrainer(int trainerId) throws ServiceException;
    List<CommentTo> findAll() throws ServiceException;
}
