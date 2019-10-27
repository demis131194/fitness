package by.epam.fitness.service.impl;

import by.epam.fitness.dao.AssignmentDao;
import by.epam.fitness.dao.impl.AssignmentDaoImpl;
import by.epam.fitness.exception.DaoException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.Assignment;
import by.epam.fitness.service.AssignmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AssignmentServiceImpl implements AssignmentService {
    private static Logger logger = LogManager.getLogger(OrderServiceImpl.class);
    private static AssignmentService assignmentService;

    private AssignmentDao assignmentDao = AssignmentDaoImpl.getInstance();

    private AssignmentServiceImpl() {
    }

    public static AssignmentService getInstance() {
        if (assignmentService == null) {
            assignmentService = new AssignmentServiceImpl();
        }
        return assignmentService;
    }

    @Override
    public Assignment create(Assignment assignment) throws ServiceException {
        logger.trace("In service method create.");
        Assignment createdAssignment;
        try {
            createdAssignment = assignmentDao.create(assignment);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return createdAssignment;
    }

    @Override
    public boolean update(Assignment assignment) throws ServiceException {
        logger.trace("In service method update.");
        boolean isUpdated;
        try {
            isUpdated = assignmentDao.update(assignment);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean delete(int assignmentId) throws ServiceException {
        logger.trace("In service method delete.");
        boolean isDeleted;
        try {
            isDeleted = assignmentDao.delete(assignmentId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return isDeleted;
    }

    @Override
    public Assignment findActive(int assignmentId) throws ServiceException {
        logger.trace("In service method findActive.");
        Assignment assignment;
        try {
            assignment = assignmentDao.findActive(assignmentId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return assignment;
    }

    @Override
    public List<Assignment> findAllActive(Integer userId, Integer trainerId) throws ServiceException {
        logger.trace("In service method findAllActive.");
        List<Assignment> assignments;
        try {
            assignments = assignmentDao.findAllActive(userId, trainerId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return assignments;
    }

    @Override
    public List<Assignment> findAll() throws ServiceException {
        logger.trace("In service method findAll.");
        List<Assignment> assignments;
        try {
            assignments = assignmentDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return assignments;
    }
}
