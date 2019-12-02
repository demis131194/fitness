package by.epam.fitness.command.impl.admin.user;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.Command;
import by.epam.fitness.command.PagePath;
import by.epam.fitness.controller.SessionRequestContent;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.user.User;
import by.epam.fitness.model.user.UserRole;
import by.epam.fitness.service.ClientService;
import by.epam.fitness.service.TrainerService;
import by.epam.fitness.service.impl.user.ClientServiceImpl;
import by.epam.fitness.service.impl.user.TrainerServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FindUserByAdmin implements Command {
    private static Logger logger = LogManager.getLogger(FindUserByAdmin.class);

    private ClientService clientService = ClientServiceImpl.getInstance();
    private TrainerService trainerService = TrainerServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        User user;
        try {
            int userId = Integer.parseInt(requestContent.getParameterByName(AttributeName.USER_ID));
            UserRole role = UserRole.valueOf(requestContent.getParameterByName(AttributeName.USER_ROLE).toUpperCase());

            switch (role) {
                case CLIENT:
                    user =  clientService.find(userId);
                    break;
                case TRAINER:
                    user = trainerService.find(userId);
                    break;
                default:
                    throw new CommandException();
            }
            page = PagePath.ADMIN_FIND_USER_PATH;
            requestContent.putAttribute(AttributeName.USER, user);

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
