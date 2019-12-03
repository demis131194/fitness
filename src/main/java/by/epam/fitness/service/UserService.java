package by.epam.fitness.service;

import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.user.User;

/**
 * The interface User service.
 */
public interface UserService {
    /**
     * Find user.
     *
     * @param userId the user id
     * @return the user
     * @throws ServiceException the service exception
     */
    User find(int userId) throws ServiceException;

    /**
     * Find by login and password user.
     *
     * @param login    the login
     * @param password the password
     * @return the user
     * @throws ServiceException the service exception
     */
    User findByLoginAndPassword(String login, String password) throws ServiceException;

    /**
     * Update user password boolean.
     *
     * @param user the user
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateUserPassword(User user) throws ServiceException;

    /**
     * Update user profile img boolean.
     *
     * @param user the user
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateUserProfileImg(User user) throws ServiceException;

    /**
     * Delete boolean.
     *
     * @param userId the user id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean delete(int userId) throws ServiceException;

    /**
     * Restore user boolean.
     *
     * @param userId the user id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean restoreUser(int userId) throws ServiceException;
}
