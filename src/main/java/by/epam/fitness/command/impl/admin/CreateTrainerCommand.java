package by.epam.fitness.command.impl.admin;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.Command;
import by.epam.fitness.command.ErrorMessageKey;
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
            String name = requestContent.getParameterByName(AttributeName.USER_NAME).strip();
            String lastName = requestContent.getParameterByName(AttributeName.USER_LAST_NAME).strip();
            String phone = requestContent.getParameterByName(AttributeName.USER_PHONE).strip();
            String login = requestContent.getParameterByName(AttributeName.USER_LOGIN).strip();
            String mail = requestContent.getParameterByName(AttributeName.USER_MAIL).strip();
            String password = requestContent.getParameterByName(AttributeName.USER_PASSWORD).strip();
            String repeatedPassword = requestContent.getParameterByName(AttributeName.REPEAT_PASSWORD).strip();

            boolean isValidParameters = true;

            if (!Validator.checkLogin(login)) {
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_LOGIN);
                isValidParameters = false;
            }
            if (!Validator.checkName(name) && isValidParameters) {
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_NAME);
                isValidParameters = false;
            }
            if (!Validator.checkLastName(lastName) && isValidParameters) {
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_LAST_NAME);
                isValidParameters = false;
            }
            if (!Validator.checkPhone(phone) && isValidParameters) {
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_PHONE);
                isValidParameters = false;
            }
            if (!Validator.checkEmail(mail) && isValidParameters) {
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_EMAIL);
                isValidParameters = false;
            }
            if (!Validator.checkPassword(password) && isValidParameters) {
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_PASSWORD);
                isValidParameters = false;
            }
            if (!repeatedPassword.equals(password) && isValidParameters) {
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.PASSWORDS_NOT_EQUAL);
                isValidParameters = false;
            }

            if (isValidParameters) {
                Trainer trainer = new Trainer();
                trainer.setName(name);
                trainer.setLastName(lastName);
                trainer.setPhone(phone);
                trainer.setLogin(login);
                trainer.setPassword(password);

                trainerService.create(trainer);
                page = PagePath.ADMIN_TRAINER_CREATED;
            } else {
                page = PagePath.ADMIN_CREATE_TRAINER;
            }

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
