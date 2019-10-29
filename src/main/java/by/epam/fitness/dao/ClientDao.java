package by.epam.fitness.dao;

import by.epam.fitness.exception.DaoException;
import by.epam.fitness.model.user.Client;

import java.util.List;

public interface ClientDao {

    Client create(Client client) throws DaoException;
    boolean update(Client client) throws DaoException;
    boolean delete(int clientId) throws DaoException;
    Client find(int clientId) throws DaoException;
    List<Client> findAllActive() throws DaoException;
    List<Client> findAllActiveByTrainerId(int trainerId) throws DaoException;
    List<Client> findAll() throws DaoException;
    List<Client> findAllActiveByName(String name) throws DaoException;
    List<Client> findAllActiveByLastName(String lastName) throws DaoException;
}