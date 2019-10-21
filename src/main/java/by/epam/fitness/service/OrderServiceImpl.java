package by.epam.fitness.service;

import by.epam.fitness.dao.OrderDao;
import by.epam.fitness.dao.OrderDaoImpl;
import by.epam.fitness.model.Order;
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
    public Order create(Order order) {
        logger.trace("In service method create.");
        return orderDao.create(order);
    }

    @Override
    public boolean update(Order order) {
        logger.trace("In service method update.");
        return orderDao.update(order);
    }

    @Override
    public boolean delete(int orderId, int userId) {
        logger.trace("In service method delete.");
        return orderDao.delete(orderId, userId);
    }

    @Override
    public Order find(int orderId, int userId) {
        logger.trace("In service method get.");
        return orderDao.find(orderId, userId);
    }

    @Override
    public List<Order> findAll(int userId) {
        logger.trace("In service method getAll.");
        return orderDao.findAll(userId);
    }
}
