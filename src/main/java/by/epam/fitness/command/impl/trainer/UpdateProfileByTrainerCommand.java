package by.epam.fitness.command.impl.trainer;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.Command;
import by.epam.fitness.command.PagePath;
import by.epam.fitness.container.SessionRequestContent;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.user.Trainer;
import by.epam.fitness.service.TrainerService;
import by.epam.fitness.service.impl.user.TrainerServiceImpl;
import by.epam.fitness.util.ErrorMessageKey;
import by.epam.fitness.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdateProfileByTrainerCommand implements Command {
    private static Logger logger = LogManager.getLogger(UpdateProfileByTrainerCommand.class);

    private TrainerService trainerService = TrainerServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        boolean isValidParameters = true;

        try {
            Integer clientId = Integer.parseInt(requestContent.getParameterByName(AttributeName.USER_ID));
            String userName = requestContent.getParameterByName(AttributeName.USER_NAME).strip();
            String userLastName = requestContent.getParameterByName(AttributeName.USER_LAST_NAME).strip();
            String userPhone = requestContent.getParameterByName(AttributeName.USER_PHONE).strip();


            if (!Validator.checkName(userName)) {
                isValidParameters = false;
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_USER_NAME);
            }
            if (!Validator.checkLastName(userLastName) && isValidParameters) {
                isValidParameters = false;
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_USER_LAST_NAME);
            }
            if (!Validator.checkPhone(userPhone) && isValidParameters) {
                isValidParameters = false;
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_USER_PHONE);
            }


            if (isValidParameters) {
                Trainer trainer = new Trainer();

                trainer.setId(clientId);
                trainer.setName(userName);
                trainer.setLastName(userLastName);
                trainer.setPhone(userPhone);
                trainerService.update(trainer);

                trainer = trainerService.find(clientId);
                requestContent.putSessionAttribute(AttributeName.USER_NAME, trainer.getName());
                requestContent.putSessionAttribute(AttributeName.USER_LAST_NAME, trainer.getLastName());
                requestContent.putSessionAttribute(AttributeName.USER_PHONE, trainer.getPhone());
                page = PagePath.TRAINER_PROFILE_PATH;
            } else {
                page = PagePath.TRAINER_PROFILE_EDIT_PATH;
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
