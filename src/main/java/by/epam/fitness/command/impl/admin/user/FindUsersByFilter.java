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

import java.util.ArrayList;
import java.util.List;

public class FindUsersByFilter implements Command {
    private static Logger logger = LogManager.getLogger(FindUsersByFilter.class);

    private ClientService clientService = ClientServiceImpl.getInstance();
    private TrainerService trainerService = TrainerServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        List<User> users = new ArrayList<>();

        try {
            String userName = requestContent.getParameterByName(AttributeName.USER_NAME).strip();
            String userLastName = requestContent.getParameterByName(AttributeName.USER_LAST_NAME).strip();
            String userRole = requestContent.getParameterByName(AttributeName.USER_ROLE).strip();

            if (userName.isBlank()) {
                userName = null;
            }
            if (userLastName.isBlank()) {
                userLastName = null;
            }

            if (userRole.matches(UserRole.CLIENT.name()) || userRole.matches(UserRole.TRAINER.name())) {
                UserRole role = UserRole.valueOf(userRole);
                switch (role) {
                    case TRAINER:
                        users.addAll(trainerService.findAllActiveByNameAndLastName(userName, userLastName));
                        break;
                    case CLIENT:
                        users.addAll(clientService.findAllActiveByNameAndLastName(userName, userLastName));
                        break;
                }
            } else {
                users.addAll(trainerService.findAllActiveByNameAndLastName(userName, userLastName));
                users.addAll(clientService.findAllActiveByNameAndLastName(userName, userLastName));
            }


            page = PagePath.ADMIN_USERS_PATH;
            requestContent.putAttribute(AttributeName.USERS, users);

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
