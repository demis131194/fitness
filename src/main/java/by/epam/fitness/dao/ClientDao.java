package by.epam.fitness.dao;

import by.epam.fitness.exception.DaoException;
import by.epam.fitness.model.Card;
import by.epam.fitness.model.user.Client;

import java.math.BigDecimal;
import java.util.List;

public interface ClientDao {

    Client create(Client client) throws DaoException;
    boolean update(Client client) throws DaoException;
    boolean verification(int clientId) throws DaoException;
    boolean depositCash(int clientId, BigDecimal depositedCash, Card card) throws DaoException;
    boolean withdrawCash(int clientId, BigDecimal depositedCash, Card card) throws DaoException;
    Client find(int clientId) throws DaoException;
    List<Client> findAllActive() throws DaoException;
    List<Client> findAll() throws DaoException;
    List<Client> findAllActiveByNameAndLastName(String name, String lastName) throws DaoException;
}
