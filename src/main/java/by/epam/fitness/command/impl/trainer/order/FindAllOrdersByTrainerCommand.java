package by.epam.fitness.command.impl.trainer.order;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.Command;
import by.epam.fitness.command.PagePath;
import by.epam.fitness.command.impl.client.order.FindAllOrdersByClientCommand;
import by.epam.fitness.container.SessionRequestContent;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.Order;
import by.epam.fitness.service.OrderService;
import by.epam.fitness.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FindAllOrdersByTrainerCommand implements Command {
    private static Logger logger = LogManager.getLogger(FindAllOrdersByTrainerCommand.class);

    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        try {
            Object obj = requestContent.getSessionAttributeByName(AttributeName.USER_ID);
            if (obj != null && obj.getClass() == Integer.class) {
                int userId = (Integer) obj;

                List<Order> orders;

                orders = orderService.findAllActiveByTrainer(userId);
                page = PagePath.TRAINER_ORDERS_PATH;
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