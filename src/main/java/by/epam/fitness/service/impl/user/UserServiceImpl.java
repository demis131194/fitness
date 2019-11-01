package by.epam.fitness.service.impl.user;

import by.epam.fitness.dao.UserDao;
import by.epam.fitness.dao.impl.UserDaoImpl;
import by.epam.fitness.exception.DaoException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.user.User;
import by.epam.fitness.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserServiceImpl implements UserService {

    private static Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private static UserService userService;

    private UserDao userDao = UserDaoImpl.getInstance();

    private UserServiceImpl() {
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserServiceImpl();
            logger.debug("UserService created.");
        }
        return userService;
    }

    @Override
    public User findByLoginAndPassword(String login, String password) throws ServiceException {
        User user;
        try {
            user = userDao.findByLoginAndPassword(login, password);
        } catch (DaoException e) {
            logger.warn(e);
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public boolean updatePassword(int userId, String password) throws ServiceException {
        boolean isUpdated;
        try {
            isUpdated = userDao.updatePassword(userId, password);
        } catch (DaoException e) {
            logger.warn(e);
            throw new ServiceException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean delete(int userId) throws ServiceException {
        boolean isDeleted;
        try {
            isDeleted = userDao.delete(userId);
        } catch (DaoException e) {
            logger.warn(e);
            throw new ServiceException(e);
        }
        return isDeleted;
    }

    @Override
    public boolean restoreUser(int userId) throws ServiceException {
        boolean isRestored;
        try {
            isRestored = userDao.restoreUser(userId);
        } catch (DaoException e) {
            logger.warn(e);
            throw new ServiceException(e);
        }
        return isRestored;
    }
}
