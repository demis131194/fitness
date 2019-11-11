package by.epam.fitness.command.impl.order;

import by.epam.fitness.command.AttributeName;
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

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class FindOrderCommand implements Command {
    private static Logger logger = LogManager.getLogger(FindAllOrdersByClientCommand.class);

    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        try {
            int id = Integer.parseInt(requestContent.getParameterByName(AttributeName.ORDER_ID));
            if (id > 0) {
               OrderTo order;
                order = orderService.find(id);
                page = PagePath.CLIENT_FIND_ORDER_PATH;
                requestContent.putAttribute(AttributeName.ORDER, order);
            } else {
                throw new CommandException("Incorrect id");
            }

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
