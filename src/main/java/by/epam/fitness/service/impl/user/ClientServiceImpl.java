package by.epam.fitness.service.impl.user;

import by.epam.fitness.dao.ClientDao;
import by.epam.fitness.dao.impl.ClientDaoImpl;
import by.epam.fitness.exception.DaoException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.Card;
import by.epam.fitness.model.user.Client;
import by.epam.fitness.service.ClientService;
import by.epam.fitness.util.PasswordEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;

public class ClientServiceImpl implements ClientService {
    private static Logger logger = LogManager.getLogger(ClientServiceImpl.class);
    private static ClientService trainerService = new ClientServiceImpl();

    private ClientDao clientDao = ClientDaoImpl.getInstance();

    private ClientServiceImpl() {
    }

    public static ClientService getInstance() {
        return trainerService;
    }

    @Override
    public Client create(Client client) throws ServiceException {
        Client createdClient;
        try {
            client.setPassword(PasswordEncoder.encode(client.getPassword()));
            createdClient = clientDao.create(client);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return createdClient;
    }

    @Override
    public boolean update(Client client) throws ServiceException {
        boolean isUpdated;
        try {
            isUpdated = clientDao.update(client);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean depositCash(int clientId, BigDecimal depositedCash, Card card) throws ServiceException {
        boolean isUpdated;
        try {
            isUpdated = clientDao.depositCash(clientId, depositedCash, card);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean withdrawCash(int clientId, BigDecimal depositedCash, Card card) throws ServiceException {
        boolean isUpdated;
        try {
            isUpdated = clientDao.withdrawCash(clientId, depositedCash, card);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return isUpdated;
    }

    @Override
    public Client find(int clientId) throws ServiceException {
        Client client;
        try {
            client = clientDao.find(clientId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return client;
    }

    @Override
    public List<Client> findAllActive() throws ServiceException {
        List<Client> clients;
        try {
            clients = clientDao.findAllActive();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return clients;
    }

    @Override
    public List<Client> findAll() throws ServiceException {
        List<Client> clients;
        try {
            clients = clientDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return clients;
    }

    @Override
    public List<Client> findAllActiveByNameAndLastName(String name, String lastName) throws ServiceException {
        List<Client> clients;
        try {
            clients = clientDao.findAllActiveByNameAndLastName(name, lastName);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return clients;
    }
}
