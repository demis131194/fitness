package by.epam.fitness.command.impl.admin.user;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.Command;
import by.epam.fitness.command.PagePath;
import by.epam.fitness.container.SessionRequestContent;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.user.Client;
import by.epam.fitness.model.user.User;
import by.epam.fitness.service.ClientService;
import by.epam.fitness.service.impl.user.ClientServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdateClientByAdminCommand implements Command {
    private static Logger logger = LogManager.getLogger(UpdateClientByAdminCommand.class);

    private ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        try {
            int userId = Integer.parseInt(requestContent.getParameterByName(AttributeName.USER_ID));
            String name = requestContent.getParameterByName(AttributeName.USER_NAME);
            String lastName = requestContent.getParameterByName(AttributeName.USER_LAST_NAME);
            String phone = requestContent.getParameterByName(AttributeName.USER_PHONE);
            Integer discount = Integer.parseInt(requestContent.getParameterByName(AttributeName.USER_DISCOUNT));
            Integer discountLevel = Integer.parseInt(requestContent.getParameterByName(AttributeName.USER_DISCOUNT_LEVEL));

            Client client = new Client();
            client.setId(userId);
            client.setName(name);
            client.setLastName(lastName);
            client.setPhone(phone);
            client.setDiscount(discount);
            client.setDiscountLevel(discountLevel);

            clientService.update(client);

            page = PagePath.ADMIN_USER_UPDATED_PATH;

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
