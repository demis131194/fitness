package by.epam.fitness.command.impl.client.order;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.Command;
import by.epam.fitness.command.PagePath;
import by.epam.fitness.controller.SessionRequestContent;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.service.OrderService;
import by.epam.fitness.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteOrderByClient implements Command {
    private static Logger logger = LogManager.getLogger(DeleteOrderByClient.class);

    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        try {
            int id = Integer.parseInt(requestContent.getParameterByName(AttributeName.ORDER_ID));
            orderService.delete(id);
            requestContent.putAttribute(AttributeName.ORDER_ID, id);
            page = PagePath.CLIENT_ORDER_DELETED_PATH;

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
