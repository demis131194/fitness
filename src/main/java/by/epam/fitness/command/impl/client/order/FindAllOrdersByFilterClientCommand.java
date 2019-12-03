package by.epam.fitness.command.impl.client.order;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.Command;
import by.epam.fitness.command.PagePath;
import by.epam.fitness.controller.SessionRequestContent;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.Order;
import by.epam.fitness.model.OrderStatus;
import by.epam.fitness.service.OrderService;
import by.epam.fitness.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;

/**
 * The type Find all orders by filter client command.
 */
public class FindAllOrdersByFilterClientCommand implements Command {
    private static Logger logger = LogManager.getLogger(FindAllOrdersByClientCommand.class);

    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        try {
            String trainerName = requestContent.getParameterByName(AttributeName.TRAINER_NAME);
            String trainerLastName = requestContent.getParameterByName(AttributeName.TRAINER_LAST_NAME);
            String startDate = requestContent.getParameterByName(AttributeName.START_DATE);
            String endDate = requestContent.getParameterByName(AttributeName.END_DATE);
            String status = requestContent.getParameterByName(AttributeName.STATUS);

            Order filter = new Order();
            if (trainerName != null && !trainerName.isBlank()) {
                filter.setTrainerName(trainerName);
            }
            if (trainerLastName != null && !trainerLastName.isBlank()) {
                filter.setTrainerLastName(trainerLastName);
            }
            if (startDate != null && !startDate.isBlank()) {
                filter.setStartDate(LocalDate.parse(startDate));
            }
            if (endDate != null && !endDate.isBlank()) {
                filter.setStartDate(LocalDate.parse(endDate));
            }
            if (status != null && !status.isBlank()) {
                filter.setOrderStatus(OrderStatus.values()[Integer.parseInt(status)]);
            } else {
                filter.setOrderStatus(null);
            }

            filter.setClientId((Integer) requestContent.getSessionAttributeByName(AttributeName.USER_ID));

            List<Order> result = orderService.findAllWithFilter(filter);

            requestContent.putAttribute(AttributeName.ORDERS, result);
            page = PagePath.CLIENT_ORDERS_PATH;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
