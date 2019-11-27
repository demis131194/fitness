package by.epam.fitness.command.impl.admin.order;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.Command;
import by.epam.fitness.command.ErrorMessageKey;
import by.epam.fitness.command.PagePath;
import by.epam.fitness.container.SessionRequestContent;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.Order;
import by.epam.fitness.model.OrderStatus;
import by.epam.fitness.service.OrderService;
import by.epam.fitness.service.impl.OrderServiceImpl;
import by.epam.fitness.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;

public class FindOrdersByFilterByAdmin implements Command {
    private static Logger logger = LogManager.getLogger(FindOrdersByFilterByAdmin.class);

    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        boolean isValidParameters = true;
        try {
            String trainerName = requestContent.getParameterByName(AttributeName.TRAINER_NAME).strip();
            String trainerLastName = requestContent.getParameterByName(AttributeName.TRAINER_LAST_NAME).strip();
            String clientName = requestContent.getParameterByName(AttributeName.CLIENT_NAME).strip();
            String clientLastName = requestContent.getParameterByName(AttributeName.CLIENT_LAST_NAME).strip();
            String startDate = requestContent.getParameterByName(AttributeName.START_DATE).strip();
            String endDate = requestContent.getParameterByName(AttributeName.END_DATE).strip();
            String status = requestContent.getParameterByName(AttributeName.STATUS).strip();
            String active = requestContent.getParameterByName(AttributeName.ACTIVE).strip();

            if (!Validator.checkName(trainerName)) {
                if (trainerName.isBlank()) {
                    trainerName = null;
                } else {
                    isValidParameters = false;
                    requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_TRAINER_NAME);
                }
            }
            if (!Validator.checkLastName(trainerLastName) && isValidParameters) {
                if (trainerLastName.isBlank()) {
                    trainerLastName = null;
                } else {
                    isValidParameters = false;
                    requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_TRAINER_LAST_NAME);
                }
            }
            if (!Validator.checkName(clientName) && isValidParameters) {
                if (clientName.isBlank()) {
                    clientName = null;
                } else {
                    isValidParameters = false;
                    requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_USER_NAME);
                }
            }
            if (!Validator.checkLastName(clientLastName) && isValidParameters) {
                if (clientLastName.isBlank()) {
                    clientLastName = null;
                } else {
                    isValidParameters = false;
                    requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_USER_LAST_NAME);
                }

            }

            if (isValidParameters) {
                Order filter = new Order();
                filter.setClientName(clientName);
                filter.setClientLastName(clientLastName);
                filter.setTrainerName(trainerName);
                filter.setTrainerLastName(trainerLastName);
                if (startDate != null && !startDate.isBlank()) {
                    filter.setStartDate(LocalDate.parse(startDate));
                }
                if (endDate != null && !endDate.isBlank()) {
                    filter.setEndDate(LocalDate.parse(endDate));
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
            } else {
//                requestContent.putAttribute(AttributeName.ORDERS, new ArrayList<Order>());
                page = PagePath.ADMIN_ORDERS_PATH;
            }

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
