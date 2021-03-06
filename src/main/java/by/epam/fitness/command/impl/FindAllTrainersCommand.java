package by.epam.fitness.command.impl;

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
 * The type Find all trainers command.
 */
public class FindAllTrainersCommand implements Command {
    private static Logger logger = LogManager.getLogger(FindAllTrainersCommand.class);

    private TrainerService orderService = TrainerServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        try {
            List<Trainer> trainers = orderService.findAll();

            requestContent.putAttribute(AttributeName.TRAINERS, trainers);

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return PagePath.ADMIN_ALL_TRAINERS_PATH;
    }
}
