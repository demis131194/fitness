package by.epam.fitness.service.impl.user;

import by.epam.fitness.dao.UserDao;
import by.epam.fitness.dao.impl.UserDaoImpl;
import by.epam.fitness.exception.DaoException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.user.User;
import by.epam.fitness.service.UserService;
import by.epam.fitness.util.PasswordEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type User service.
 */
public class UserServiceImpl implements UserService {

    private static Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private static UserService userService = new UserServiceImpl();

    private UserDao userDao = UserDaoImpl.getInstance();

    private UserServiceImpl() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static UserService getInstance() {
        return userService;
    }

    @Override
    public User find(int userId) throws ServiceException {
        User user;
        try {
            user = userDao.find(userId);
        } catch (DaoException e) {
            logger.warn(e);
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public User findByLoginAndPassword(String login, String password) throws ServiceException {
        User user;
        try {
            String encodedPassword = PasswordEncoder.encode(password);
            user = userDao.findByLoginAndPassword(login, encodedPassword);
        } catch (DaoException e) {
            logger.warn(e);
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public boolean updateUserPassword(User user) throws ServiceException {
        boolean isUpdated;
        try {
            user.setPassword(PasswordEncoder.encode(user.getPassword()));
            isUpdated = userDao.updateUserPassword(user);
        } catch (DaoException e) {
            logger.warn(e);
            throw new ServiceException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean updateUserProfileImg(User user) throws ServiceException {
        boolean isUpdated;
        try {
            isUpdated = userDao.updateUserProfileImg(user);
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
