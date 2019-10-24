package by.epam.fitness.service.impl;

import by.epam.fitness.dao.UserDao;
import by.epam.fitness.dao.UserDaoImpl;
import by.epam.fitness.exception.DaoException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.User;
import by.epam.fitness.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private static UserService orderService;

    private UserDao userDao = UserDaoImpl.getInstance();

    private UserServiceImpl() {
    }

    public static UserService getInstance() {
        if (orderService == null) {
            orderService = new UserServiceImpl();
            logger.debug("UserService created.");
        }
        return orderService;
    }

    @Override
    public User create(User user) throws ServiceException {
        User createdUser;
        try {
            createdUser = userDao.create(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return createdUser;
    }

    @Override
    public boolean update(User user) throws ServiceException {
        boolean isUpdated;
        try {
            isUpdated = userDao.update(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        boolean isDeleted;
        try {
            isDeleted = userDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return isDeleted;
    }

    @Override
    public List<User> findAllActive() throws ServiceException {
        List<User> activeUsers;
        try {
            activeUsers = userDao.findAllActive();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return activeUsers;
    }

    @Override
    public List<User> findAll() throws ServiceException {
        List<User> allUsers;
        try {
            allUsers = userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return allUsers;
    }

    @Override
    public User findByLogin(String userLogin) throws ServiceException {
        User userByLogin;
        try {
            userByLogin = userDao.findByLogin(userLogin);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return userByLogin;
    }

    @Override
    public User findByLoginAndPassword(String userLogin, String userPassword) throws ServiceException {
        User userByLogin;
        try {
            userByLogin = userDao.findByLoginAndPassword(userLogin, userPassword);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return userByLogin;
    }
}
