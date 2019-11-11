package by.epam.fitness.dao;

import by.epam.fitness.exception.DaoException;
import by.epam.fitness.model.Order;
import by.epam.fitness.to.OrderTo;

import java.util.List;

public interface OrderDao {
    Order create(Order order) throws DaoException;
    boolean update(Order order) throws DaoException;
    boolean delete(int orderId) throws DaoException;
    OrderTo find(int orderId) throws DaoException;
    List<OrderTo> findAll() throws DaoException;
    List<OrderTo> findAllWithFilter(OrderTo filter) throws DaoException;
    List<OrderTo> findAllActive() throws DaoException;
    List<OrderTo> findAllActiveByTrainer(int trainerId) throws DaoException;
    List<OrderTo> findAllActiveByClient(int clientId) throws DaoException;
}
