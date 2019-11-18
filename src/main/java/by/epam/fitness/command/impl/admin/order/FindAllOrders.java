package by.epam.fitness.command.impl.admin.order;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.Command;
import by.epam.fitness.command.PagePath;
import by.epam.fitness.container.SessionRequestContent;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.Order;
import by.epam.fitness.service.OrderService;
import by.epam.fitness.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FindAllOrders implements Command {
    private static Logger logger = LogManager.getLogger(FindAllOrders.class);

    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        try {
            List<Order> orders = orderService.findAll();
            page = PagePath.ADMIN_ORDERS_PATH;
            requestContent.putAttribute(AttributeName.ORDERS, orders);

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
