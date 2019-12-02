package by.epam.fitness.command.impl.client.order;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.Command;
import by.epam.fitness.command.ErrorMessageKey;
import by.epam.fitness.command.PagePath;
import by.epam.fitness.controller.SessionRequestContent;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.FitnessCard;
import by.epam.fitness.model.Order;
import by.epam.fitness.model.OrderStatus;
import by.epam.fitness.model.user.Client;
import by.epam.fitness.service.ClientService;
import by.epam.fitness.service.OrderService;
import by.epam.fitness.service.impl.OrderServiceImpl;
import by.epam.fitness.service.impl.user.ClientServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdateOrderByClientCommand implements Command {
    private static Logger logger = LogManager.getLogger(UpdateOrderByClientCommand.class);

    private OrderService orderService = OrderServiceImpl.getInstance();
    private ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        Order order = null;
        try {
            int orderId = Integer.parseInt(requestContent.getParameterByName(AttributeName.ORDER_ID));
            String comment = requestContent.getParameterByName(AttributeName.COMMENT).strip().replaceAll("<script>", "").replaceAll("</script>", "");;
            int orderStatusOrdinal = Integer.parseInt(requestContent.getParameterByName(AttributeName.STATUS));
            OrderStatus status = OrderStatus.values()[orderStatusOrdinal];

            order = orderService.find(orderId);

            if (status == OrderStatus.ACCEPTED) {
                int userId = (Integer) requestContent.getSessionAttributeByName(AttributeName.USER_ID);
                clientService.withdrawCash(userId, order.getPrice(), FitnessCard.FITNESS_CARD);
                Client client = clientService.find(userId);
                requestContent.putSessionAttribute(AttributeName.USER_CASH, client.getCash());
            }

            order.setId(orderId);
            order.setClientComment(comment);
            order.setOrderStatus(status);

            orderService.update(order);
            page = PagePath.CLIENT_ORDER_UPDATED;
        } catch (ServiceException e) {
            requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.ACCOUNT_NOT_ENOUGH_AMOUNT);
            requestContent.putAttribute(AttributeName.ORDER, order);
            page = PagePath.CLIENT_UPDATE_ORDER;
        }

        return page;
    }
}
