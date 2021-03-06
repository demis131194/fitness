package by.epam.fitness.command.impl.client;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.Command;
import by.epam.fitness.command.PagePath;
import by.epam.fitness.controller.SessionRequestContent;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.user.Trainer;
import by.epam.fitness.service.TrainerService;
import by.epam.fitness.service.impl.user.TrainerServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The type Show create order page command.
 */
public class ShowCreateOrderPageCommand implements Command {
    private static Logger logger = LogManager.getLogger(ShowCreateOrderPageCommand.class);

    private TrainerService trainerService = TrainerServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        try {
            List<Trainer> trainers = trainerService.findAll();
            requestContent.putAttribute(AttributeName.TRAINERS, trainers);
            page = PagePath.CLIENT_CREATE_ORDER;

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
