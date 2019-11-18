package by.epam.fitness.command.impl.admin.order;

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

import java.time.LocalDate;
import java.util.List;

public class FindUsersByFilterByAdmin implements Command {
    private static Logger logger = LogManager.getLogger(FindUsersByFilterByAdmin.class);

    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        try {
            String trainerName = requestContent.getParameterByName(AttributeName.TRAINER_NAME);
            String trainerLastName = requestContent.getParameterByName(AttributeName.TRAINER_LAST_NAME);
            String clientName = requestContent.getParameterByName(AttributeName.CLIENT_NAME);
            String clientLastName = requestContent.getParameterByName(AttributeName.CLIENT_LAST_NAME);
            String startDate = requestContent.getParameterByName(AttributeName.START_DATE);
            String endDate = requestContent.getParameterByName(AttributeName.END_DATE);
            String status = requestContent.getParameterByName(AttributeName.STATUS);
            String active = requestContent.getParameterByName(AttributeName.ACTIVE);

            //todo VALIDATION
            Order filter = new Order();
            if (clientName != null && !clientName.isBlank()) {
                filter.setClientName(clientName);
            }
            if (clientLastName != null && !clientLastName.isBlank()) {
                filter.setClientLastName(clientLastName);
            }
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
            }
            if (active != null && !active.isBlank()) {
                filter.setActive(Boolean.parseBoolean(active));
            } else {
                filter.setActive(null);
            }

            List<Order> result = orderService.findAllWithFilter(filter);

            requestContent.putAttribute(AttributeName.ORDERS, result);
            page = PagePath.ADMIN_ORDERS_PATH;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
