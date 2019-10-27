package by.epam.fitness.dao;

import by.epam.fitness.exception.DaoException;
import by.epam.fitness.model.User;

import java.util.List;

public interface UserDao {

    User create(User user) throws DaoException;
    boolean update(User user) throws DaoException;
    boolean delete(int id) throws DaoException;
    List<User> findAllActive() throws DaoException;
    List<User> findAllActiveWithTrainer(int trainerId) throws DaoException;
    List<User> findAll() throws DaoException;
    User findByLogin(String userLogin) throws DaoException;
    User findByLoginAndPassword(String userLogin, String userPassword) throws DaoException;
}
