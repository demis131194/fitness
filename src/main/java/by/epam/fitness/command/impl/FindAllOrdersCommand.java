package by.epam.fitness.command.impl;

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

import java.util.ArrayList;
import java.util.List;

public class FindAllOrdersCommand implements Command {
    private static Logger logger = LogManager.getLogger(FindAllOrdersCommand.class);

    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        try {
            Object obj = requestContent.getSessionAttributeByName("userId");
            if (obj != null && obj.getClass() == Integer.class) {
                int userId = (Integer) obj;

                String role = (String) requestContent.getSessionAttributeByName("userRole");

                List<Order> orders;

                switch (role) {
                    case "ADMIN":
                        orders = orderService.findAll();
                        page = PagePath.ADMIN_ORDERS_PATH;
                        break;
                    case "CLIENT":
                        orders = orderService.findAllActiveByClient(userId);
                        page = PagePath.CLIENT_ORDERS_PATH;
                        break;
                    case "TRAINER":
                        orders = orderService.findAllActiveByTrainer(userId);
                        page = PagePath.TRAINER_ORDERS_PATH;
                        break;
                    default:
                        throw new CommandException("User hasn't have role");
                }
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
