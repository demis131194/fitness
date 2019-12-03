package by.epam.fitness.command.impl.client.order;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.Command;
import by.epam.fitness.command.PagePath;
import by.epam.fitness.controller.SessionRequestContent;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.Order;
import by.epam.fitness.model.TrainingDuration;
import by.epam.fitness.service.OrderService;
import by.epam.fitness.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * The type Create order command.
 */
public class CreateOrderCommand implements Command {
    private static Logger logger = LogManager.getLogger(CreateOrderCommand.class);

    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        try {
            int clientId = (Integer) requestContent.getSessionAttributeByName(AttributeName.USER_ID);
            int discount = (Integer) requestContent.getSessionAttributeByName(AttributeName.USER_DISCOUNT);
            int trainerId = Integer.parseInt(requestContent.getParameterByName(AttributeName.TRAINER_ID));
            String clientComment = requestContent.getParameterByName(AttributeName.COMMENT).strip().replaceAll("<script>", "").replaceAll("</script>", "");;
            String date = requestContent.getParameterByName(AttributeName.START_DATE);
            LocalDate startDate = LocalDate.parse(date);
            String duration = requestContent.getParameterByName(AttributeName.DURATION);
            TrainingDuration training = TrainingDuration.values()[Integer.parseInt(duration)];
            LocalDate endDate = startDate.plus(training.getDurationDay(), ChronoUnit.DAYS);
            BigDecimal price = training.getPrice().multiply(new BigDecimal(1d - 1d/discount), MathContext.DECIMAL32);

            Order order = new Order();
            order.setClientId(clientId);
            order.setTrainerId(trainerId);
            order.setClientComment(clientComment);
            order.setStartDate(startDate);
            order.setEndDate(endDate);
            order.setPrice(price);

            orderService.create(order);
            page = PagePath.CLIENT_ORDER_CREATED;

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
