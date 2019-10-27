package by.epam.fitness.service;

import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.User;

import java.util.List;

public interface UserService {

    User create(User user) throws ServiceException;
    boolean update(User user) throws ServiceException;
    boolean delete(int id) throws ServiceException;
    List<User> findAllActive() throws ServiceException;
    List<User> findAllActiveWithTrainer(int trainerId) throws ServiceException;
    List<User> findAll() throws ServiceException;
    User findByLogin(String userLogin) throws ServiceException;
    User findByLoginAndPassword(String userLogin, String userPassword) throws ServiceException;
}
