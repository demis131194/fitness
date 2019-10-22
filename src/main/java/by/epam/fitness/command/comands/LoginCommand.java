package by.epam.fitness.command.comands;

import by.epam.fitness.command.Command;
import by.epam.fitness.constants.PagePaths;
import by.epam.fitness.container.SessionRequestContent;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.model.User;
import by.epam.fitness.service.UserService;
import by.epam.fitness.service.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private static Logger logger = LogManager.getLogger(LoginCommand.class);

    private static final String PARAM_NAME_LOGIN = "Login";
    private static final String PARAM_NAME_PASSWORD = "Password";

    private static final ResourceBundle bundle;

    static {
        Locale.setDefault(Locale.US);                           // FIXME: 22.10.2019 change locale after
        bundle = ResourceBundle.getBundle("err");
    }

    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String login = requestContent.getParameterByName(PARAM_NAME_LOGIN);
        String password = requestContent.getParameterByName(PARAM_NAME_PASSWORD);

        User user;
        String page;
        try {
            user = userService.findByLoginAndPassword(login, password);

            if (user != null) {
                requestContent.putSessionAttribute("role", user.getUserRole().name());
                requestContent.putSessionAttribute("user", user);
                page = PagePaths.WELCOME_PATH;
            } else {
                requestContent.putAttribute("errorLoginPassMessage", bundle.getString("wrong.login.or.pass"));
                page = PagePaths.LOGIN_PATH;
            }

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
