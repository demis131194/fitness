package by.epam.fitness.dao;

import by.epam.fitness.model.Order;
import by.epam.fitness.util.cp.ConnectionPool;

import java.util.List;

public class OrderDaoImpl implements OrderDao {

    ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public Order create(Order order) {
        return null;
    }

    @Override
    public Order update(Order order) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Order get(int id) {
        return null;
    }

    @Override
    public List<Order> getAll(int userId) {
        return null;
    }
}
