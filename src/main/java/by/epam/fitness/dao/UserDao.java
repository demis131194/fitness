package by.epam.fitness.dao;

import by.epam.fitness.exception.DaoException;
import by.epam.fitness.model.user.User;

/**
 * The interface User dao.
 */
public interface UserDao {
    /**
     * Find user.
     *
     * @param userId the user id
     * @return the user
     * @throws DaoException the dao exception
     */
    User find(int userId) throws DaoException;

    /**
     * Find by login and password user.
     *
     * @param login    the login
     * @param password the password
     * @return the user
     * @throws DaoException the dao exception
     */
    User findByLoginAndPassword(String login, String password) throws DaoException;

    /**
     * Update user password boolean.
     *
     * @param user the user
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateUserPassword(User user) throws DaoException;

    /**
     * Update user profile img boolean.
     *
     * @param user the user
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateUserProfileImg(User user) throws DaoException;

    /**
     * Delete boolean.
     *
     * @param userId the user id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean delete(int userId) throws DaoException;

    /**
     * Restore user boolean.
     *
     * @param userId the user id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean restoreUser(int userId) throws DaoException;
}
