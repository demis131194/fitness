package by.epam.fitness.service.impl;

import by.epam.fitness.dao.OrderDao;
import by.epam.fitness.dao.impl.OrderDaoImpl;
import by.epam.fitness.exception.DaoException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.Order;
import by.epam.fitness.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private static Logger logger = LogManager.getLogger(OrderServiceImpl.class);
    private static OrderService orderService;

    private OrderDao orderDao = OrderDaoImpl.getInstance();

    private OrderServiceImpl() {
    }

    public static OrderService getInstance() {
        if (orderService == null) {
            orderService = new OrderServiceImpl();
        }
        return orderService;
    }

    @Override
    public Order create(Order order) throws ServiceException {
        logger.trace("In service method create order.");
        Order createdOrder;
        try {
            createdOrder = orderDao.create(order);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return createdOrder;
    }

    @Override
    public boolean update(Order order) throws ServiceException {
        boolean isUpdated;
        try {
            isUpdated = orderDao.update(order);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean delete(int orderId) throws ServiceException {
        boolean isDeleted;
        try {
            isDeleted = orderDao.delete(orderId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return isDeleted;
    }

    @Override
    public Order find(int orderId) throws ServiceException {
        Order order;
        try {
            order = orderDao.find(orderId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return order;
    }

    @Override
    public List<Order> findAll() throws ServiceException {
        List<Order> orders;
        try {
            orders = orderDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return orders;
    }

    @Override
    public List<Order> findAllWithFilter(Order filter) throws ServiceException {
        List<Order> orders;
        try {
            orders = orderDao.findAllWithFilter(filter);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return orders;
    }

    @Override
    public List<Order> findAllActive() throws ServiceException {
        List<Order> orders;
        try {
            orders = orderDao.findAllActive();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return orders;
    }

    @Override
    public List<Order> findAllActiveByTrainer(int trainerId) throws ServiceException {
        List<Order> orders;
        try {
            orders = orderDao.findAllActiveByTrainer(trainerId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return orders;
    }

    @Override
    public List<Order> findAllActiveByClient(int clientId) throws ServiceException {
        List<Order> orders;
        try {
            orders = orderDao.findAllActiveByClient(clientId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return orders;
    }
}
