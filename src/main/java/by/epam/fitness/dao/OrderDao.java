package by.epam.fitness.dao;

import by.epam.fitness.model.Order;

import java.util.List;

public interface OrderDao {
    Order create(Order order);
    Order update(Order order);
    boolean delete(int id);
    Order get(int id);
    List<Order> getAll(int userId);
}
