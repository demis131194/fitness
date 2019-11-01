package by.epam.fitness.service;

import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.Order;

import java.util.List;

public interface OrderService {

    Order create(Order order) throws ServiceException;
    boolean update(Order order) throws ServiceException;
    boolean delete(int orderId) throws ServiceException;
    Order find(int orderId) throws ServiceException;
    List<Order> findAll() throws ServiceException;
    List<Order> findAllWithFilter(Order filter) throws ServiceException;
    List<Order> findAllActive() throws ServiceException;
    List<Order> findAllActiveByTrainer(int trainerId) throws ServiceException;
    List<Order> findAllActiveByClient(int clientId) throws ServiceException;
}
