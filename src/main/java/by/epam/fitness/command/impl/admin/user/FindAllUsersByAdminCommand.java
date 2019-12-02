package by.epam.fitness.command.impl.admin.user;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.Command;
import by.epam.fitness.command.PagePath;
import by.epam.fitness.controller.SessionRequestContent;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.user.User;
import by.epam.fitness.service.ClientService;
import by.epam.fitness.service.TrainerService;
import by.epam.fitness.service.impl.user.ClientServiceImpl;
import by.epam.fitness.service.impl.user.TrainerServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class FindAllUsersByAdminCommand implements Command {
    private static Logger logger = LogManager.getLogger(FindAllUsersByAdminCommand.class);

    private ClientService clientService = ClientServiceImpl.getInstance();
    private TrainerService trainerService = TrainerServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        try {
            List<User> users = new ArrayList<>();

            users.addAll(clientService.findAll());
            users.addAll(trainerService.findAll());
            page = PagePath.ADMIN_USERS_PATH;
            requestContent.putAttribute(AttributeName.USERS, users);

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
