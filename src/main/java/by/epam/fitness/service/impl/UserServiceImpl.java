package by.epam.fitness.service.impl;

import by.epam.fitness.dao.UserDao;
import by.epam.fitness.dao.impl.UserDaoImpl;
import by.epam.fitness.exception.DaoException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.user.Client;
import by.epam.fitness.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private static UserService orderService;

    private UserDao userDao = UserDaoImpl.getInstance();

    private UserServiceImpl() {
    }

    public static UserService getInstance() {
        if (orderService == null) {
            orderService = new UserServiceImpl();
            logger.debug("UserService created.");
        }
        return orderService;
    }

    @Override
    public Client create(Client client) throws ServiceException {
        Client createdClient;
        try {
            createdClient = userDao.create(client);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return createdClient;
    }

    @Override
    public boolean update(Client client) throws ServiceException {
        boolean isUpdated;
        try {
            isUpdated = userDao.update(client);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        boolean isDeleted;
        try {
            isDeleted = userDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return isDeleted;
    }

    @Override
    public List<Client> findAllActive() throws ServiceException {
        List<Client> activeClients;
        try {
            activeClients = userDao.findAllActive();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return activeClients;
    }

    @Override
    public List<Client> findAllActiveWithTrainer(int trainerId) throws ServiceException {
        List<Client> activeClients;
        try {
            activeClients = userDao.findAllActiveWithTrainer(trainerId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return activeClients;
    }

    @Override
    public List<Client> findAll() throws ServiceException {
        List<Client> allClients;
        try {
            allClients = userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return allClients;
    }

    @Override
    public Client findByLogin(String userLogin) throws ServiceException {
        Client clientByLogin;
        try {
            clientByLogin = userDao.findByLogin(userLogin);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return clientByLogin;
    }

    @Override
    public Client findByLoginAndPassword(String userLogin, String userPassword) throws ServiceException {
        Client clientByLogin;
        try {
            clientByLogin = userDao.findByLoginAndPassword(userLogin, userPassword);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return clientByLogin;
    }
}
