package by.epam.fitness.controller;

import by.epam.fitness.model.Order;
import by.epam.fitness.service.OrderService;
import by.epam.fitness.service.OrderServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class OrderServlet extends HttpServlet {

    private OrderService orderService;
    private final int userId = 1;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        orderService = OrderServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orders = orderService.getAll(userId);
//        List<Order> orders = Arrays.asList(
//                new Order(1, 1, LocalDate.of(2001, 10, 15), LocalDate.of(2001, 11, 15), 200, "Text-1"),
//                new Order(2, 1, LocalDate.of(2001, 11, 15), LocalDate.of(2001, 12, 15), 200, "Text-2")
//        );
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/orders.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
