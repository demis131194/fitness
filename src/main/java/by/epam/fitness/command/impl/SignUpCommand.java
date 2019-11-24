package by.epam.fitness.command.impl;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.Command;
import by.epam.fitness.command.PagePath;
import by.epam.fitness.container.SessionRequestContent;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.user.Client;
import by.epam.fitness.service.ClientService;
import by.epam.fitness.service.impl.user.ClientServiceImpl;
import by.epam.fitness.util.ErrorMessageKey;
import by.epam.fitness.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SignUpCommand implements Command {
    private static Logger logger = LogManager.getLogger(SignUpCommand.class);

    private ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;

        String login = requestContent.getParameterByName(AttributeName.USER_LOGIN).strip();
        String password = requestContent.getParameterByName(AttributeName.USER_PASSWORD).strip();
        String repeatedPassword = requestContent.getParameterByName(AttributeName.REPEAT_PASSWORD).strip();
        String name = requestContent.getParameterByName(AttributeName.USER_NAME).strip();
        String lastName = requestContent.getParameterByName(AttributeName.USER_LAST_NAME).strip();
        String phone = requestContent.getParameterByName(AttributeName.USER_PHONE).strip();
        String mail = requestContent.getParameterByName(AttributeName.USER_MAIL).strip();

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

        try {

            if (isValidParameters) {
                Client client = new Client();
                client.setLogin(login);
                client.setPassword(password);
                client.setName(name);
                client.setLastName(lastName);
                client.setPhone(phone);
                client.setMail(mail);

                clientService.create(client);
                page = PagePath.CLIENT_CREATED;

            } else {
                page = PagePath.SIGN_UP_PATH;
            }

            requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INCORRECT_INPUT_DATA);

        } catch (ServiceException e) {
            page = PagePath.SIGN_UP_PATH;
            requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.LOGIN_ALREADY_EXIST);
        }
        return page;
    }
}
