package by.epam.fitness.command.impl.client.order;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.Command;
import by.epam.fitness.command.PagePath;
import by.epam.fitness.container.SessionRequestContent;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.Order;
import by.epam.fitness.model.OrderStatus;
import by.epam.fitness.service.OrderService;
import by.epam.fitness.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdateOrderByClientCommand implements Command {
    private static Logger logger = LogManager.getLogger(UpdateOrderByClientCommand.class);

    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        try {
            Integer orderId = Integer.parseInt(requestContent.getParameterByName(AttributeName.ORDER_ID));
            String comment = requestContent.getParameterByName(AttributeName.COMMENT);
            int orderStatusOrdinal = Integer.parseInt(requestContent.getParameterByName(AttributeName.STATUS));
            OrderStatus status = OrderStatus.values()[orderStatusOrdinal];

            Order order = new Order();
            order.setId(orderId);
            order.setClientComment(comment);
            order.setOrderStatus(status);

            orderService.update(order);
            page = PagePath.CLIENT_ORDER_UPDATED;

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
