package by.epam.fitness.command.impl;

import by.epam.fitness.command.Command;
import by.epam.fitness.command.PagePath;
import by.epam.fitness.container.SessionRequestContent;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.service.OrderService;
import by.epam.fitness.service.impl.OrderServiceImpl;
import by.epam.fitness.to.OrderTo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FindAllOrdersByClientCommand implements Command {
    private static Logger logger = LogManager.getLogger(FindAllOrdersByClientCommand.class);

    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        try {
            Object obj = requestContent.getSessionAttributeByName("userId");
            if (obj != null && obj.getClass() == Integer.class) {
                int userId = (Integer) obj;

                List<OrderTo> orders;

                orders = orderService.findAllActiveByClient(userId);
                page = PagePath.CLIENT_ORDERS_PATH;
                requestContent.putAttribute("orders", orders);
            } else {
                throw new CommandException("No user in session");
            }

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
