package by.epam.fitness.dao;

import by.epam.fitness.exception.DaoException;
import by.epam.fitness.model.Order;

import java.util.List;

public interface OrderDao {
    Order create(Order order) throws DaoException;
    boolean update(Order order) throws DaoException;
    boolean delete(int orderId, int userId) throws DaoException;
    Order findActive(int orderId, int userId) throws DaoException;
    List<Order> findAllActive(int userId) throws DaoException;
    List<Order> findAll() throws DaoException;
}
