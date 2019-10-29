package by.epam.fitness.command.impl;

import by.epam.fitness.command.Command;
import by.epam.fitness.controller.PagePath;
import by.epam.fitness.container.SessionRequestContent;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.user.Client;
import by.epam.fitness.service.UserService;
import by.epam.fitness.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.format.DateTimeFormatter;

public class LoginCommand implements Command {
    private static Logger logger = LogManager.getLogger(LoginCommand.class);

    private static final String PARAM_NAME_LOGIN = "Login";
    private static final String PARAM_NAME_PASSWORD = "Password";

    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String login = requestContent.getParameterByName(PARAM_NAME_LOGIN);
        String password = requestContent.getParameterByName(PARAM_NAME_PASSWORD);

        Client client;
        String page;
        try {
            client = userService.findByLoginAndPassword(login, password);

            if (client != null) {
                requestContent.putSessionAttribute("authorization", true);
                requestContent.putSessionAttribute("userId", client.getId());
                requestContent.putSessionAttribute("userRole", client.getUserRole().name());
                requestContent.putSessionAttribute("userName", client.getName());
                requestContent.putSessionAttribute("userLastName", client.getLastName());
                requestContent.putSessionAttribute("userLogin", client.getLogin());
                requestContent.putSessionAttribute("userRegisterDate", client.getRegisterDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                page = PagePath.WELCOME_PATH;
            } else {
                page = PagePath.LOGIN_PATH;
                requestContent.putAttribute("wrongPassOrLogin", true);
            }

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
