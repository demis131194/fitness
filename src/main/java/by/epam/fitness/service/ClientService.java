package by.epam.fitness.service;

import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.Card;
import by.epam.fitness.model.user.Client;

import java.math.BigDecimal;
import java.util.List;

/**
 * The interface Client service.
 */
public interface ClientService {

    /**
     * Create client.
     *
     * @param client the client
     * @return the client
     * @throws ServiceException the service exception
     */
    Client create(Client client) throws ServiceException;

    /**
     * Update boolean.
     *
     * @param client the client
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean update(Client client) throws ServiceException;

    /**
     * Verification boolean.
     *
     * @param clientId the client id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean verification(int clientId) throws ServiceException;

    /**
     * Deposit cash boolean.
     *
     * @param clientId      the client id
     * @param depositedCash the deposited cash
     * @param card          the card
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean depositCash(int clientId, BigDecimal depositedCash, Card card) throws ServiceException;

    /**
     * Withdraw cash boolean.
     *
     * @param clientId      the client id
     * @param depositedCash the deposited cash
     * @param card          the card
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean withdrawCash(int clientId, BigDecimal depositedCash, Card card) throws ServiceException;

    /**
     * Find client.
     *
     * @param clientId the client id
     * @return the client
     * @throws ServiceException the service exception
     */
    Client find(int clientId) throws ServiceException;

    /**
     * Find all active list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Client> findAllActive() throws ServiceException;

    /**
     * Find all list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Client> findAll() throws ServiceException;

    /**
     * Find all active by name and last name list.
     *
     * @param name     the name
     * @param lastName the last name
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Client> findAllActiveByNameAndLastName(String name, String lastName) throws ServiceException;
}
