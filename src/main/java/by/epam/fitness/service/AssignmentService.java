package by.epam.fitness.service;

import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.Assignment;

import java.util.List;

public interface AssignmentService {
    Assignment create(Assignment assignment) throws ServiceException;
    boolean update(Assignment assignment) throws ServiceException;
    boolean delete(int assignmentId) throws ServiceException;
    Assignment findActive(int assignmentId) throws ServiceException;
    List<Assignment> findAllActive(Integer userId, Integer trainerId) throws ServiceException;
    List<Assignment> findAll() throws ServiceException;
}
