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
        logger.trace("In service method create.");
        Order createdOder;
        try {
            createdOder = orderDao.create(order);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return createdOder;
    }

    @Override
    public boolean update(Order order) throws ServiceException {
        logger.trace("In service method update.");
        boolean isUpdated;
        try {
            isUpdated = orderDao.update(order);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean delete(int orderId, int userId) throws ServiceException {
        logger.trace("In service method delete.");
        boolean isDeleted;
        try {
            isDeleted = orderDao.delete(orderId, userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return isDeleted;
    }

    @Override
    public Order findActive(int orderId, Integer userId, Integer trainerId) throws ServiceException {
        logger.trace("In service method findActive.");
        Order order;
        try {
            order = orderDao.findActive(orderId, userId, trainerId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return order;
    }

    @Override
    public List<Order> findAllActive(Integer userId, Integer trainerId) throws ServiceException {
        logger.trace("In service method findAllActive.");
        List<Order> orders;
        try {
            orders = orderDao.findAllActive(userId, trainerId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return orders;
    }

    @Override
    public List<Order> findAll() throws ServiceException {
        logger.trace("In service method findAll.");
        List<Order> orders;
        try {
            orders = orderDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return orders;
    }
}
