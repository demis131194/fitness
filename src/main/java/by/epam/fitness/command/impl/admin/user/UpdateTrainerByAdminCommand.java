package by.epam.fitness.command.impl.admin.user;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.Command;
import by.epam.fitness.command.ErrorMessageKey;
import by.epam.fitness.command.PagePath;
import by.epam.fitness.controller.SessionRequestContent;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.user.Trainer;
import by.epam.fitness.service.TrainerService;
import by.epam.fitness.service.impl.user.TrainerServiceImpl;
import by.epam.fitness.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdateTrainerByAdminCommand implements Command {
    private static Logger logger = LogManager.getLogger(UpdateTrainerByAdminCommand.class);

    private TrainerService trainerService = TrainerServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        boolean isValidParameters = true;
        try {
            int userId = Integer.parseInt(requestContent.getParameterByName(AttributeName.USER_ID));
            String name = requestContent.getParameterByName(AttributeName.USER_NAME);
            String lastName = requestContent.getParameterByName(AttributeName.USER_LAST_NAME);
            String phone = requestContent.getParameterByName(AttributeName.USER_PHONE);
            String mail = requestContent.getParameterByName(AttributeName.USER_MAIL);

            if (!Validator.checkName(name)) {
                isValidParameters = false;
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_USER_NAME);
            }
            if (!Validator.checkLastName(lastName) && isValidParameters) {
                isValidParameters = false;
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_USER_LAST_NAME);
            }
            if (!Validator.checkPhone(phone) && isValidParameters) {
                isValidParameters = false;
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_USER_PHONE);
            }
            if (!Validator.checkEmail(mail) && isValidParameters) {
                isValidParameters = false;
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_EMAIL);
            }

            if (isValidParameters) {
                Trainer trainer = new Trainer();
                trainer.setId(userId);
                trainer.setName(name);
                trainer.setLastName(lastName);
                trainer.setPhone(phone);
                trainer.setMail(mail);

                trainerService.update(trainer);

                page = PagePath.ADMIN_USER_UPDATED_PATH;
            } else {
                Trainer trainer = trainerService.find(userId);
                requestContent.putAttribute(AttributeName.USER, trainer);
                page = PagePath.ADMIN_UPDATE_USER_PATH;
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }

}
