package by.epam.fitness.service;

import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.user.User;

public interface UserService {
    User find(int userId) throws ServiceException;
    User findByLoginAndPassword(String login, String password) throws ServiceException;
    boolean updateUserPassword(User user) throws ServiceException;
    boolean updateUserProfileImg(User user) throws ServiceException;
    boolean delete(int userId) throws ServiceException;
    boolean restoreUser(int userId) throws ServiceException;
}
