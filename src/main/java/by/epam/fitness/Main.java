package by.epam.fitness;

import by.epam.fitness.dao.OrderDao;
import by.epam.fitness.dao.OrderDaoImpl;
import by.epam.fitness.model.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Main {
    private static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException, SQLException {

        OrderDao orderDao = new OrderDaoImpl();
//        Order order = new Order(1,
//                LocalDate.of(2010, 10, 15),
//                LocalDate.of(2010, 11, 15),
//                250,
//                "TEST-11111"
//        );
        List<Order> orders = orderDao.getAll(2);
        Order order = orderDao.get(3, 1);

        System.out.println(orders);
        System.out.println(order);
    }
}
