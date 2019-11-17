package by.epam.fitness.service;

import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.user.Client;

import java.math.BigDecimal;
import java.util.List;

public interface ClientService {

    Client create(Client client) throws ServiceException;
    boolean update(Client client) throws ServiceException;
    boolean updateCash(int clientId, BigDecimal depositedCash) throws ServiceException;
    Client find(int clientId) throws ServiceException;
    List<Client> findAllActive() throws ServiceException;
    List<Client> findAll() throws ServiceException;
    List<Client> findAllActiveByNameAndLastName(String name, String lastName) throws ServiceException;
}
