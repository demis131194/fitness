package by.epam.fitness.dao;

import by.epam.fitness.model.Order;

import java.util.List;

public interface OrderDao {
    Order create(Order order);
    boolean update(Order order);
    boolean delete(int orderId, int userId);
    Order get(int orderId, int userId);
    List<Order> getAll(int userId);
}
