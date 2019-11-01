package by.epam.fitness.service.impl.user;

import by.epam.fitness.dao.ClientDao;
import by.epam.fitness.dao.impl.ClientDaoImpl;
import by.epam.fitness.exception.DaoException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.user.Client;
import by.epam.fitness.service.ClientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;

public class ClientServiceImpl implements ClientService {
    private static Logger logger = LogManager.getLogger(ClientServiceImpl.class);
    private static ClientService trainerService;

    private ClientDao clientDao = ClientDaoImpl.getInstance();

    private ClientServiceImpl() {
    }

    public static ClientService getInstance() {
        if (trainerService == null) {
            trainerService = new ClientServiceImpl();
            logger.debug("ClientService created.");
        }
        return trainerService;
    }

    @Override
    public Client create(Client client) throws ServiceException {
        Client clientTrainer;
        try {
            clientTrainer = clientDao.create(client);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return clientTrainer;
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
    public boolean updateCash(int clientId, BigDecimal depositedCash) throws ServiceException {
        boolean isUpdated;
        try {
            isUpdated = clientDao.updateCash(clientId, depositedCash);
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
    public List<Client> findAllActiveByName(String name) throws ServiceException {
        List<Client> clients;
        try {
            clients = clientDao.findAllActiveByName(name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return clients;
    }

    @Override
    public List<Client> findAllActiveByLastName(String lastName) throws ServiceException {
        List<Client> clients;
        try {
            clients = clientDao.findAllActiveByLastName(lastName);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return clients;
    }
}
