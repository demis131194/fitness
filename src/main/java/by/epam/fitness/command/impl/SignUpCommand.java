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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SignUpCommand implements Command {
    private static Logger logger = LogManager.getLogger(LoginCommand.class);

    private static final String PARAM_NAME_LOGIN = "Login";
    private static final String PARAM_NAME_PASSWORD = "Password";
    private static final String PARAM_NAME_NAME = "Name";
    private static final String PARAM_NAME_LAST_NAME = "LastName";

    private ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;

        Client client = new Client();
        client.setLogin(requestContent.getParameterByName(PARAM_NAME_LOGIN));
        client.setPassword(requestContent.getParameterByName(PARAM_NAME_PASSWORD));
        client.setName(requestContent.getParameterByName(PARAM_NAME_NAME));
        client.setLastName(requestContent.getParameterByName(PARAM_NAME_LAST_NAME));

        try {
            clientService.create(client);
            page = PagePath.CLIENT_CREATED;
        } catch (ServiceException e) {
            requestContent.putAttribute(AttributeName.ERR_WRONG_LOGIN, true);
            page = PagePath.SIGN_UP_PATH;
        }
        return page;
    }
}
