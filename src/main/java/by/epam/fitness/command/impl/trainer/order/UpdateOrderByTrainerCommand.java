package by.epam.fitness.command.impl.trainer.order;

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

public class UpdateOrderByTrainerCommand implements Command {
    private static Logger logger = LogManager.getLogger(UpdateOrderByTrainerCommand.class);

    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        try {
            int orderId = Integer.parseInt(requestContent.getParameterByName(AttributeName.ORDER_ID));
            String exercises = requestContent.getParameterByName(AttributeName.EXERCISES).strip().replaceAll("<script>", "").replaceAll("</script>", "");
            String nutrition = requestContent.getParameterByName(AttributeName.NUTRITION).strip().replaceAll("<script>", "").replaceAll("</script>", "");;
            OrderStatus status = OrderStatus.REVIEWED;

            Order order = new Order();
            order.setId(orderId);
            order.setExercises(exercises);
            order.setNutrition(nutrition);
            order.setOrderStatus(status);

            orderService.update(order);
            page = PagePath.TRAINER_ORDER_UPDATED;

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
