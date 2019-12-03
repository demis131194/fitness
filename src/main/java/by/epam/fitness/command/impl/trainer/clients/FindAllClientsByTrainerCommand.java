package by.epam.fitness.command.impl.trainer.clients;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.Command;
import by.epam.fitness.command.PagePath;
import by.epam.fitness.controller.SessionRequestContent;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.Order;
import by.epam.fitness.model.OrderStatus;
import by.epam.fitness.model.user.User;
import by.epam.fitness.service.ClientService;
import by.epam.fitness.service.OrderService;
import by.epam.fitness.service.impl.OrderServiceImpl;
import by.epam.fitness.service.impl.user.ClientServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The type Find all clients by trainer command.
 */
public class FindAllClientsByTrainerCommand implements Command {
    private static Logger logger = LogManager.getLogger(FindAllClientsByTrainerCommand.class);

    private OrderService orderService = OrderServiceImpl.getInstance();
    private ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        try {
            int trainerId = (Integer) requestContent.getSessionAttributeByName(AttributeName.USER_ID);
            List<Order> trainerOrders = orderService.findAllActiveByTrainer(trainerId);
            Set<Integer> usersId = trainerOrders.stream()
                    .filter(order -> order.getOrderStatus() != OrderStatus.TERMINATED)
                    .map(Order::getClientId)
                    .collect(Collectors.toSet());


            List<User> users = new ArrayList<>();

            for (Integer id : usersId) {
                User user = clientService.find(id);
                users.add(user);
            }

            page = PagePath.TRAINER_CLIENTS_PATH;
            requestContent.putAttribute(AttributeName.USERS, users);

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
