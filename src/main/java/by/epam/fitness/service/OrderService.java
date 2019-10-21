package by.epam.fitness.service;

import by.epam.fitness.model.Order;

import java.util.List;

public interface OrderService {

    Order create(Order order);
    boolean update(Order order);
    boolean delete(int orderId, int userId);
    Order find(int orderId, int userId);
    List<Order> findAll(int userId);
}
