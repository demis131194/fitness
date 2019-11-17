package by.epam.fitness.command.impl.admin.user;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.Command;
import by.epam.fitness.command.PagePath;
import by.epam.fitness.container.SessionRequestContent;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.user.Trainer;
import by.epam.fitness.service.TrainerService;
import by.epam.fitness.service.impl.user.TrainerServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdateTrainerByAdminCommand implements Command {
    private static Logger logger = LogManager.getLogger(UpdateTrainerByAdminCommand.class);

    private TrainerService trainerService = TrainerServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        try {
            int userId = Integer.parseInt(requestContent.getParameterByName(AttributeName.USER_ID));
            String name = requestContent.getParameterByName(AttributeName.USER_NAME);
            String lastName = requestContent.getParameterByName(AttributeName.USER_LAST_NAME);
            String phone = requestContent.getParameterByName(AttributeName.USER_PHONE);

            Trainer trainer = new Trainer();
            trainer.setId(userId);
            trainer.setName(name);
            trainer.setLastName(lastName);
            trainer.setPhone(phone);

            trainerService.update(trainer);

            page = PagePath.ADMIN_USER_UPDATED_PATH;

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }

}
