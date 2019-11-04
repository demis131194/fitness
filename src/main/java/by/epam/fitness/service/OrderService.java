package by.epam.fitness.service;

import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.Order;
import by.epam.fitness.to.OrderTo;

import java.util.List;

public interface OrderService {

    Order create(Order order) throws ServiceException;
    boolean update(Order order) throws ServiceException;
    boolean delete(int orderId) throws ServiceException;
    OrderTo find(int orderId) throws ServiceException;
    List<OrderTo> findAll() throws ServiceException;
    List<OrderTo> findAllWithFilter(Order filter) throws ServiceException;
    List<OrderTo> findAllActive() throws ServiceException;
    List<OrderTo> findAllActiveByTrainer(int trainerId) throws ServiceException;
    List<OrderTo> findAllActiveByClient(int clientId) throws ServiceException;
}
