package by.epam.fitness.dao;

import by.epam.fitness.exception.DaoException;
import by.epam.fitness.model.Order;

import java.util.List;

public interface OrderDao {
    Order create(Order order) throws DaoException;
    boolean update(Order order) throws DaoException;
    boolean delete(int orderId) throws DaoException;
    Order find(int orderId) throws DaoException;
    List<Order> findAll() throws DaoException;
    List<Order> findAllActive() throws DaoException;
    List<Order> findAllActiveByTrainer(int trainerId) throws DaoException;
    List<Order> findAllActiveByClient(int clientId) throws DaoException;
}
