package by.epam.fitness.command.impl.admin;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.Command;
import by.epam.fitness.command.PagePath;
import by.epam.fitness.container.SessionRequestContent;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.user.Trainer;
import by.epam.fitness.service.TrainerService;
import by.epam.fitness.service.impl.user.TrainerServiceImpl;
import by.epam.fitness.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateTrainerCommand implements Command {
    private static Logger logger = LogManager.getLogger(CreateTrainerCommand.class);

    private TrainerService trainerService = TrainerServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        try {
            //todo VALIDATION
            String userName = requestContent.getParameterByName(AttributeName.USER_NAME);
            String userLastName = requestContent.getParameterByName(AttributeName.USER_LAST_NAME);
            String phone = requestContent.getParameterByName(AttributeName.USER_PHONE);
            String login = requestContent.getParameterByName(AttributeName.USER_LOGIN);
            String pass = requestContent.getParameterByName(AttributeName.USER_PASSWORD);
            String repeatPass = requestContent.getParameterByName(AttributeName.REPEAT_PASSWORD);


            Trainer trainer = new Trainer();
            trainer.setName(userName);
            trainer.setLastName(userLastName);
            trainer.setPhone(phone);
            trainer.setLogin(login);
            trainer.setPassword(pass);



            trainerService.create(trainer);
            page = PagePath.ADMIN_TRAINER_CREATED;

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
