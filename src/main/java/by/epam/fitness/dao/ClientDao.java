package by.epam.fitness.dao;

import by.epam.fitness.exception.DaoException;
import by.epam.fitness.model.user.Client;

import java.util.List;

public interface ClientDao {

    Client create(Client client) throws DaoException;
    boolean update(Client client) throws DaoException;
    boolean delete(int id) throws DaoException;
    List<Client> findAllActive() throws DaoException;
    List<Client> findAllActiveWithTrainer(int trainerId) throws DaoException;
    List<Client> findAll() throws DaoException;
    Client findByLogin(String userLogin) throws DaoException;
    Client findByLoginAndPassword(String userLogin, String userPassword) throws DaoException;
}
