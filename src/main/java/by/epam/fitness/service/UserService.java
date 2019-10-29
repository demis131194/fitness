package by.epam.fitness.service;

import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.user.Client;

import java.util.List;

public interface UserService {

    Client create(Client client) throws ServiceException;
    boolean update(Client client) throws ServiceException;
    boolean delete(int id) throws ServiceException;
    List<Client> findAllActive() throws ServiceException;
    List<Client> findAllActiveWithTrainer(int trainerId) throws ServiceException;
    List<Client> findAll() throws ServiceException;
    Client findByLogin(String userLogin) throws ServiceException;
    Client findByLoginAndPassword(String userLogin, String userPassword) throws ServiceException;
}
