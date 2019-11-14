package by.epam.fitness.command.impl.order;

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

import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class CreateOrderCommand implements Command {
    private static Logger logger = LogManager.getLogger(CreateOrderCommand.class);

    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        try {
            int clientId = (Integer) requestContent.getSessionAttributeByName(AttributeName.USER_ID);
            int trainerId = Integer.parseInt(requestContent.getParameterByName(AttributeName.TRAINER_ID));
            String clientComment = requestContent.getParameterByName(AttributeName.COMMENT);
            Order order = new Order();
            order.setClientId(clientId);
            order.setTrainerId(trainerId);
            order.setClientComment(clientComment);

            orderService.create(order);
            page = PagePath.CLIENT_ORDER_CREATED;

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
