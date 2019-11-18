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

public class RestoreOrderByAdmin implements Command {
    private static Logger logger = LogManager.getLogger(RestoreOrderByAdmin.class);

    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        try {
            int id = Integer.parseInt(requestContent.getParameterByName(AttributeName.ORDER_ID));
            Order order = new Order();
            order.setId(id);
            order.setActive(true);
            orderService.update(order);
            requestContent.putAttribute(AttributeName.ORDER_ID, id);
            page = PagePath.ADMIN_ORDER_RESTORED_PATH;

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
