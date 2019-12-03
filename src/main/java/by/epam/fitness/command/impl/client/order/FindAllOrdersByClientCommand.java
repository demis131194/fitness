package by.epam.fitness.command.impl.client.order;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.Command;
import by.epam.fitness.command.PagePath;
import by.epam.fitness.controller.SessionRequestContent;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.Order;
import by.epam.fitness.service.OrderService;
import by.epam.fitness.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The type Find all orders by client command.
 */
public class FindAllOrdersByClientCommand implements Command {
    private static Logger logger = LogManager.getLogger(FindAllOrdersByClientCommand.class);

    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        try {
            Object obj = requestContent.getSessionAttributeByName(AttributeName.USER_ID);
            if (obj != null && obj.getClass() == Integer.class) {
                int userId = (Integer) obj;

                List<Order> orders;

                orders = orderService.findAllActiveByClient(userId);
                page = PagePath.CLIENT_ORDERS_PATH;
                requestContent.putAttribute(AttributeName.ORDERS, orders);
            } else {
                throw new CommandException("No user in session");
            }

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
