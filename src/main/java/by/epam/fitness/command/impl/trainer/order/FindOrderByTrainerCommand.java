package by.epam.fitness.command.impl.trainer.order;

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

/**
 * The type Find order by trainer command.
 */
public class FindOrderByTrainerCommand implements Command {
    private static Logger logger = LogManager.getLogger(FindOrderByTrainerCommand.class);

    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        try {
            int id = Integer.parseInt(requestContent.getParameterByName(AttributeName.ORDER_ID));
            Order order = orderService.find(id);
            page = PagePath.TRAINER_FIND_ORDER_PATH;
            requestContent.putAttribute(AttributeName.ORDER, order);

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
