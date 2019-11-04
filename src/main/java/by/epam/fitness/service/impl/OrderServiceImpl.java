package by.epam.fitness.service.impl;

import by.epam.fitness.dao.OrderDao;
import by.epam.fitness.dao.impl.OrderDaoImpl;
import by.epam.fitness.exception.DaoException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.Order;
import by.epam.fitness.service.OrderService;
import by.epam.fitness.to.OrderTo;
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
    public OrderTo find(int orderId) throws ServiceException {
        OrderTo orderTo;
        try {
            orderTo = orderDao.find(orderId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return orderTo;
    }

    @Override
    public List<OrderTo> findAll() throws ServiceException {
        List<OrderTo> orderTos;
        try {
            orderTos = orderDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return orderTos;
    }

    @Override
    public List<OrderTo> findAllWithFilter(Order filter) throws ServiceException {
        List<OrderTo> orderTos;
        try {
            orderTos = orderDao.findAllWithFilter(filter);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return orderTos;
    }

    @Override
    public List<OrderTo> findAllActive() throws ServiceException {
        List<OrderTo> orderTos;
        try {
            orderTos = orderDao.findAllActive();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return orderTos;
    }

    @Override
    public List<OrderTo> findAllActiveByTrainer(int trainerId) throws ServiceException {
        List<OrderTo> orderTos;
        try {
            orderTos = orderDao.findAllActiveByTrainer(trainerId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return orderTos;
    }

    @Override
    public List<OrderTo> findAllActiveByClient(int clientId) throws ServiceException {
        List<OrderTo> orderTos;
        try {
            orderTos = orderDao.findAllActiveByClient(clientId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return orderTos;
    }
}
