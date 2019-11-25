package by.epam.fitness.dao;

import by.epam.fitness.exception.DaoException;
import by.epam.fitness.model.user.User;

public interface UserDao {
    User find(int userId) throws DaoException;
    User findByLoginAndPassword(String login, String password) throws DaoException;
    boolean updateUserPassword(User user) throws DaoException;
    boolean updateUserProfileImg(User user) throws DaoException;
    boolean delete(int userId) throws DaoException;
    boolean restoreUser(int userId) throws DaoException;
}
