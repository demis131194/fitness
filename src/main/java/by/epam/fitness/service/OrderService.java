package by.epam.fitness.service;

import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.Order;

import java.util.List;

public interface OrderService {

    Order create(Order order) throws ServiceException;
    boolean update(Order order) throws ServiceException;
    boolean delete(int orderId, int userId) throws ServiceException;
    Order findActive(int orderId, Integer userId, Integer trainerId) throws ServiceException;
    List<Order> findAllActive(Integer userId, Integer trainerId) throws ServiceException;
    List<Order> findAll() throws ServiceException;
}
