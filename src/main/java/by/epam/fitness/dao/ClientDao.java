package by.epam.fitness.dao;

import by.epam.fitness.exception.DaoException;
import by.epam.fitness.model.Card;
import by.epam.fitness.model.user.Client;

import java.math.BigDecimal;
import java.util.List;

/**
 * The interface Client dao.
 */
public interface ClientDao {

    /**
     * Create client.
     *
     * @param client the client
     * @return the client
     * @throws DaoException the dao exception
     */
    Client create(Client client) throws DaoException;

    /**
     * Update boolean.
     *
     * @param client the client
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean update(Client client) throws DaoException;

    /**
     * Verification boolean.
     *
     * @param clientId the client id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean verification(int clientId) throws DaoException;

    /**
     * Deposit cash boolean.
     *
     * @param clientId      the client id
     * @param depositedCash the deposited cash
     * @param card          the card
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean depositCash(int clientId, BigDecimal depositedCash, Card card) throws DaoException;

    /**
     * Withdraw cash boolean.
     *
     * @param clientId      the client id
     * @param depositedCash the deposited cash
     * @param card          the card
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean withdrawCash(int clientId, BigDecimal depositedCash, Card card) throws DaoException;

    /**
     * Find client.
     *
     * @param clientId the client id
     * @return the client
     * @throws DaoException the dao exception
     */
    Client find(int clientId) throws DaoException;

    /**
     * Find all active list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Client> findAllActive() throws DaoException;

    /**
     * Find all list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Client> findAll() throws DaoException;

    /**
     * Find all active by name and last name list.
     *
     * @param name     the name
     * @param lastName the last name
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Client> findAllActiveByNameAndLastName(String name, String lastName) throws DaoException;
}
