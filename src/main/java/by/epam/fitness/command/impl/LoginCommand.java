package by.epam.fitness.command.impl;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.Command;
import by.epam.fitness.command.PagePath;
import by.epam.fitness.container.SessionRequestContent;
import by.epam.fitness.exception.CommandException;
import by.epam.fitness.exception.ServiceException;
import by.epam.fitness.exception.ValidatorExcepton;
import by.epam.fitness.model.user.Admin;
import by.epam.fitness.model.user.Client;
import by.epam.fitness.model.user.Trainer;
import by.epam.fitness.model.user.User;
import by.epam.fitness.service.AdminService;
import by.epam.fitness.service.ClientService;
import by.epam.fitness.service.TrainerService;
import by.epam.fitness.service.UserService;
import by.epam.fitness.service.impl.user.AdminServiceImpl;
import by.epam.fitness.service.impl.user.ClientServiceImpl;
import by.epam.fitness.service.impl.user.TrainerServiceImpl;
import by.epam.fitness.service.impl.user.UserServiceImpl;
import by.epam.fitness.util.ErrMessageKey;
import by.epam.fitness.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginCommand implements Command {
    private static Logger logger = LogManager.getLogger(LoginCommand.class);

    private static final String PARAM_NAME_LOGIN = "Login";
    private static final String PARAM_NAME_PASSWORD = "Password";

    private UserService userService = UserServiceImpl.getInstance();
    private AdminService adminService = AdminServiceImpl.getInstance();
    private TrainerService trainerService = TrainerServiceImpl.getInstance();
    private ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String login = requestContent.getParameterByName(PARAM_NAME_LOGIN);
        String password = requestContent.getParameterByName(PARAM_NAME_PASSWORD);

        User user;
        String page;
        try {
            Validator.checkLoginAndPassword(login, password);
            user = userService.findByLoginAndPassword(login, password);

            if (user != null) {
                requestContent.putSessionAttribute(AttributeName.USER_ID, user.getId());

                switch (user.getRole()) {
                    case ADMIN:
                        Admin admin = adminService.find(user.getId());
                        requestContent.putSessionAttribute(AttributeName.USER_ROLE, admin.getRole().name());
                        requestContent.putSessionAttribute(AttributeName.USER_NAME, admin.getName());
                        requestContent.putSessionAttribute(AttributeName.USER_LAST_NAME, admin.getLastName());
                        page = PagePath.WELCOME_PATH;
                        break;
                    case TRAINER:
                        Trainer trainer = trainerService.find(user.getId());
                        requestContent.putSessionAttribute(AttributeName.USER_ROLE, trainer.getRole().name());
                        requestContent.putSessionAttribute(AttributeName.USER_NAME, trainer.getName());
                        requestContent.putSessionAttribute(AttributeName.USER_LAST_NAME, trainer.getLastName());
                        requestContent.putSessionAttribute(AttributeName.USER_REGISTER_DATE, trainer.getRegisterDateTime());
                        requestContent.putSessionAttribute(AttributeName.USER_PHONE, trainer.getPhone());
                        page = PagePath.WELCOME_PATH;
                        break;
                    case CLIENT:
                        Client client = clientService.find(user.getId());
                        requestContent.putSessionAttribute(AttributeName.USER_ROLE, client.getRole().name());
                        requestContent.putSessionAttribute(AttributeName.USER_NAME, client.getName());
                        requestContent.putSessionAttribute(AttributeName.USER_LAST_NAME, client.getLastName());
                        requestContent.putSessionAttribute(AttributeName.USER_REGISTER_DATE, client.getRegisterDateTime());
                        requestContent.putSessionAttribute(AttributeName.USER_DISCOUNT, client.getDiscount());
                        requestContent.putSessionAttribute(AttributeName.USER_DISCOUNT_LEVEL, client.getDiscountLevel());
                        requestContent.putSessionAttribute(AttributeName.USER_PHONE, client.getPhone());
                        requestContent.putSessionAttribute(AttributeName.USER_CASH, client.getCash());
                        page = PagePath.WELCOME_PATH;
                        break;
                    default:
                        throw new CommandException("User has not have init field role");
                }
                requestContent.putSessionAttribute(AttributeName.USER_AUTHORIZATION, true);
                requestContent.putSessionAttribute(AttributeName.CURRENT_PAGE, page);
            } else {
                page = (String) requestContent.getSessionAttributeByName(AttributeName.CURRENT_PAGE);
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrMessageKey.WRONG_LOGIN_OR_PASSWORD);
            }

        } catch (ServiceException e) {
            throw new CommandException(e);
        } catch (ValidatorExcepton validatorExcepton) {
            page = (String) requestContent.getSessionAttributeByName(AttributeName.CURRENT_PAGE);
            requestContent.putAttribute(AttributeName.ERR_MESSAGE, validatorExcepton.getMessage());
        }

        return page;
    }
}
