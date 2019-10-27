package by.epam.fitness.dao;

import by.epam.fitness.exception.DaoException;
import by.epam.fitness.model.Assignment;

import java.util.List;

public interface AssignmentDao {
    Assignment create(Assignment order) throws DaoException;
    boolean update(Assignment order) throws DaoException;
    boolean delete(int assignmentId) throws DaoException;
    Assignment findActive(int assignmentId) throws DaoException;
    List<Assignment> findAllActive(Integer userId, Integer trainerId) throws DaoException;
    List<Assignment> findAll() throws DaoException;
}
