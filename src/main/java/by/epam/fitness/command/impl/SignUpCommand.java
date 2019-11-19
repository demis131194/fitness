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
import by.epam.fitness.util.ErrMessageKey;
import by.epam.fitness.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SignUpCommand implements Command {
    private static Logger logger = LogManager.getLogger(LoginCommand.class);

    private static final String PARAM_LOGIN = "Login";
    private static final String PARAM_PASSWORD = "Password";
    private static final String PARAM_REPEAT_PASSWORD = "RepeatPassword";
    private static final String PARAM_NAME = "Name";
    private static final String PARAM_LAST_NAME = "LastName";
    private static final String PARAM_PHONE = "Phone";

    private ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;

        String login = requestContent.getParameterByName(PARAM_LOGIN);
        String password = requestContent.getParameterByName(PARAM_PASSWORD);
        String repeatedPassword = requestContent.getParameterByName(PARAM_REPEAT_PASSWORD);
        String name = requestContent.getParameterByName(PARAM_NAME);
        String lastName = requestContent.getParameterByName(PARAM_LAST_NAME);
        String phone = requestContent.getParameterByName(PARAM_PHONE);

        try {
            if (Validator.checkLogin(login)
                    && Validator.checkPassword(password, repeatedPassword)
                    && Validator.checkName(name)
                    && Validator.checkLastName(lastName)
                    && Validator.checkPhone(phone)) {

                Client client = new Client();
                client.setLogin(login);
                client.setPassword(password);
                client.setName(name);
                client.setLastName(lastName);
                client.setPhone(phone);

                clientService.create(client);
                page = PagePath.CLIENT_CREATED;
            } else {
                page = (String) requestContent.getSessionAttributeByName(AttributeName.CURRENT_PAGE);
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrMessageKey.INCORRECT_INPUT_DATA);
            }


        } catch (ServiceException e) {
            page = (String) requestContent.getSessionAttributeByName(AttributeName.CURRENT_PAGE);
            requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrMessageKey.LOGIN_ALREADY_EXIST);
        }
        return page;
    }
}
