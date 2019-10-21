package by.epam.fitness.dao;

import by.epam.fitness.model.Order;

import java.util.List;

public interface OrderDao {
    Order create(Order order);
    boolean update(Order order);
    boolean delete(int orderId, int userId);
    Order find(int orderId, int userId);
    List<Order> findAll(int userId);
}
