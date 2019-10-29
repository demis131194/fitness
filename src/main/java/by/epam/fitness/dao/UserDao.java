package by.epam.fitness.dao;

import by.epam.fitness.exception.DaoException;
import by.epam.fitness.model.user.User;

public interface UserDao {
    User findByLoginAndPassword(String login, String password) throws DaoException;
    boolean updatePassword(int userId, String password) throws DaoException;
}
