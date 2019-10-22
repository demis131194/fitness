package by.epam.fitness.command.comands;

import by.epam.fitness.command.Command;
import by.epam.fitness.constants.PagePaths;
import by.epam.fitness.container.SessionRequestContent;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.Order;
import by.epam.fitness.model.User;
import by.epam.fitness.service.OrderService;
import by.epam.fitness.service.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FindAllOrdersCommand implements Command {
    private static Logger logger = LogManager.getLogger(FindAllOrdersCommand.class);

    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        try {
            Object obj = requestContent.getSessionAttributeByName("user");
            if (obj != null && obj.getClass() == User.class) {
                User user = (User) obj;
                int userId = user.getId();

                List<Order> orders = orderService.findAll(userId);

                requestContent.putAttribute("orders", orders);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return PagePaths.ORDERS_PATH;
    }
}
